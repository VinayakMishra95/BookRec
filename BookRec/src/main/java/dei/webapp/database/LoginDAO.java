/*
 * Copyright 2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dei.webapp.database;

import dei.webapp.resource.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Searches users by their name in order to login them.
 *
 * @version 1.00
 * @since 1.00
 */
public class LoginDAO extends AbstractDAO<User>  {

	/**
	 * The SQL statement to be executed
	 */
    private static final String STATEMENT_LOGIN = "SELECT * FROM users WHERE name=? AND password=md5(?);";

	/**
	 * The name of the user
	 */
    private final User user;

	/**
	 * Creates a new object for searching users by name.
	 *
	 * @param con    the connection to the database.
	 * @param user the user to login.
	 */
    public LoginDAO(final Connection con, final User user) {
        super(con);
        this.user = user;
    }

    @Override
    protected final void doAccess() throws Exception {
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        // the results of the search
        User user_new = null;
        try {
            stmnt = con.prepareStatement(STATEMENT_LOGIN);
            stmnt.setString(1, user.getName());
            stmnt.setString(2, user.getPassword());
            rs = stmnt.executeQuery();



            if(rs.next()){

                user_new = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                LOGGER.info("User logged in {}.", user_new.getEmail());

            }else{
                LOGGER.error("error logging in the user {}",user.getName());

            }

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (stmnt != null) {
                stmnt.close();
            }

        }
        this.outputParam = user_new;
    }
}
