/**
 * This business service handles most general traffic coming from the social
 * service on the website. The class implements the SocialBuisnessInterface and
 * the methods from that interface.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torre
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.SocialDAO;
import com.gcu.model.Social;

public class SocialBusinessService implements SocialBusinessInterface {

	@Autowired
	private SocialDAO dao; //Changed to private per rubric feedback

	/**
	 * This method invokes the create method in SocialDAO to create a new social profile for the
	 * user submitting their information.
	 * @param t This is the social profile being created.
	 * @return boolean This is whether or not the creation was a success.
	 */
	@Override
	public boolean createSocial(Social t) {
		return dao.create(t);
	}

	/**
	 * This method checks to see if the user has created a social account. If they have, their navbar will
	 * allow them to pages that are restricted to others.
	 * @param id This is the user id being checked for social profile existence.
	 * @return boolean This is wheether or not the user has a social profile.
	 */
	@Override
	public boolean checkSocial(int id) {
		if(dao.findById(id) != null) { //NULL will be returned if there is no data in the db for this ID.
			return true;
		}
		else {
			return false;
		}
	}
}
