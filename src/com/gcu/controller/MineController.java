/**
 * This class is the controller for the minesweeper game in
 * the social portion of Apoco.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.1
 * @since   2018-11-25
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.Button;
import com.gcu.utilities.MinesweeperLogic;

@Controller
@RequestMapping("/mines")
public class MineController {
	
	private MinesweeperLogic ml;
	
	/**
	 * Dependency injection for Minesweeper Logic
	 * @param ml This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setUserService(MinesweeperLogic ml) {
		this.ml = ml;
	} 
	
	private int size; //Used multiple times in controller
	
	/**
	 * This method generates the game board and sets the size for the board.
	 * It then returns a multidimensional array of minesweeper buttons to the view.
	 * @param difficulty This is used to set the board size.
	 * @param session This session is used to set the size of the board.
	 * @return ModelAndView This is the buttonholder being sent to the minesweeper view.
	 */
	@RequestMapping(path="/start", method=RequestMethod.POST)
	public ModelAndView startGame(@RequestParam("diff") int difficulty, HttpSession session){
		MinesweeperLogic.setLose(false); //Sets lose to false, so they don't lose immediately upon start
		MinesweeperLogic.setWin(false); //See above
		
		size = (difficulty * 5) + 5; //Creates the board size
		
		session.setAttribute("size", size);
		ml.generateBoard(size); //Generates the minesweeper board
		
		return new ModelAndView("minesweeper", "button", MinesweeperLogic.getBtnHolder());
	}
	
	/**
	 * This method handles user left clicks in the game. The input is taken and split
	 * then run with the game logic to determine the results.
	 * @param btn This is the button that was clicked by the user.
	 * @return ModelAndView This is the buttonholder being sent back to the minesweeper view.
	 */
	@RequestMapping(path="/left", method=RequestMethod.POST)
	public ModelAndView leftClick(@RequestParam("btn") String btn) {
		String[] coor = btn.split(" "); //Splits btn into an array of coordinates
		int x = Integer.parseInt((coor[0])); //Sets x as first coordinate
		int y = Integer.parseInt((coor[1])); //Sets y as second coordinate
		
		MinesweeperLogic.processCell(x, y, size); //This processes the game logic for the cell they clicked
		MinesweeperLogic.checkWin(size); //Checks to see if the user has won the game
		
		return new ModelAndView("minesweeper", "button", MinesweeperLogic.getBtnHolder());
	}
	
	/**
	 * This method gets x and y from the http post sent through javascript. The minesweeper
	 * button is set to flagged (or unflagged) and the gameboard is returned again.
	 * @param btn This is the button that was right clicked by the user.
	 * @return ModelAndView This is the Buttonholder being sent back to the minesweeper view.
	 */
	@RequestMapping(path="/right", method=RequestMethod.POST)
	public ModelAndView rightClick(@RequestParam("btn") String btn) {
		Button[][] btnHolder = MinesweeperLogic.getBtnHolder(); //Grabs the buttonholder from minelogic
		String[] coor = btn.split(" ");
		int x = Integer.parseInt((coor[0]));
		int y = Integer.parseInt((coor[1]));
		if(btnHolder[x][y].isFlagged()) { 
			btnHolder[x][y].setFlagged(false);
		}
		else {
			btnHolder[x][y].setFlagged(true);
		}
		return new ModelAndView("minesweeper", "button", MinesweeperLogic.getBtnHolder());
	}
}
