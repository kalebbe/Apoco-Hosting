/**
 * This controller handles all things related to messages.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-11-12
 */

package com.gcu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.MessageBusinessInterface;
import com.gcu.model.Message;

@Controller
@RequestMapping("/messages")
public class MessageController {
	private MessageBusinessInterface ms;
	
	/**
	 * Dependency injection for the message business service.
	 * @param ms
	 */
	@Autowired
	public void setMessageService(MessageBusinessInterface ms) {
		this.ms = ms;
	}
	
	/**
	 * This method controls the sending of messages for all platforms.
	 * @return ModelAndView
	 */
	@RequestMapping(path ="/send", method= RequestMethod.POST)
	public ModelAndView sendMessage(HttpSession session, @RequestParam("body") String body,
			@RequestParam("userId") int uid, @RequestParam("parentId") int pid) {
		if(ms.sendMessage((int)session.getAttribute("id"), uid, pid, 
				body, (String)session.getAttribute("theme"))) {
			session.setAttribute("message2", "Message sent!");
		}
		else {
			session.setAttribute("message", "Message failed to send!");
		}
		
		List<Message> msgs = ms.getMessages((int)session.getAttribute("id"),
				(String)session.getAttribute("theme"), "RECEIVER_ID");
		
		session.setAttribute("msgType", "inbox");
		return new ModelAndView("messageCenter", "messages", msgs);		
	}
	
	/**
	 * Gets all parent messages for the user's social/dating/business inbox.
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path="/inbox", method= RequestMethod.GET)
	public ModelAndView getInbox(HttpSession session) {
		List<Message> msgs = ms.getMessages((int)session.getAttribute("id"), 
				(String)session.getAttribute("theme"), "RECEIVER_ID");
	
		session.setAttribute("msgType", "inbox");
		return new ModelAndView("messageCenter", "messages", msgs);
	}
	
	/**
	 * Gets all parent messages for the user's social/dating/business outbox.
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path="/outbox", method= RequestMethod.GET)
	public ModelAndView getOutbox(HttpSession session) {
		List<Message> msgs = ms.getMessages((int)session.getAttribute("id"),
				(String)session.getAttribute("theme"), "SENDER_ID");
		
		session.setAttribute("msgType", "outbox");
		return new ModelAndView("messageCenter", "messages", msgs);
	}
	
	/**
	 * Retrieves a thread of messages within a parent message
	 * @param session
	 * @param id
	 * @return ModelAndView
	 */
	@RequestMapping(path="/viewMessage", method= RequestMethod.GET)
	public ModelAndView viewMessage(HttpSession session, @RequestParam("id") int id) {		
		List<Message> msgs = ms.getThread(id, (String)session.getAttribute("theme"));
		
		session.setAttribute("recId", ms.getCorrespondentId((int)session.getAttribute("id"), msgs.get(0)));
		
		//This was originally done in the Business service; however, the datasource was nulling
		//out and not allowing this transaction. Because of that, it was moved here.
		for(Message msg : msgs) {         
			if(msg.getSenderId() != (int)session.getAttribute("id")) {
				ms.markRead(msg, (String)session.getAttribute("theme"));
			}
		}
		
		return new ModelAndView("viewMessage", "messages", msgs);
	}
}
