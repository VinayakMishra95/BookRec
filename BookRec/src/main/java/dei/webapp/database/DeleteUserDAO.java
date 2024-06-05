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
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Creates a new user into the database.
 *
 * @version 1.00
 * @since 1.00
 */
public final class DeleteUserDAO extends AbstractDAO<Integer> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "DELETE FROM users WHERE name=? AND email=? AND password=md5(?);";

	/**
	 /**
	 * The user to be stored into the database
	 */
	private final User user;

	/**
	 * Creates a new object for storing an user into the database.
	 *
	 * @param con
	 *            the connection to the database.
	 * @param user
	 *            the user to be stored into the database.
	 */
	public DeleteUserDAO(final Connection con, final User user) {
		super(con);

		if (user == null) {
			LOGGER.error("The user cannot be null.");
			throw new NullPointerException("The user cannot be null.");
		}

		this.user = user;
	}

	@Override
	protected final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		int to_del=0;
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());

			to_del = pstmt.executeUpdate();

			LOGGER.info("Account %s deleted from the database.", user.getName());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}
		this.outputParam = to_del;
	}
}
