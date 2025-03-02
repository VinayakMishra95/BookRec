/*
 * Copyright 2018-2023 University of Padua, Italy
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches users by their name.
 *
 * @version 1.00
 * @since 1.00
 */
public final class SearchUserDAO extends AbstractDAO<List<User>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM Users WHERE LOWER(name) LIKE ?";

	/**
	 * The name of the user
	 */
	private final String name;

	/**
	 * Creates a new object for searching users by name.
	 *
	 * @param con    the connection to the database.
	 * @param name the name of the user.
	 */
	public SearchUserDAO(final Connection con, final String name) {
		super(con);
		this.name = name;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<User> users = new ArrayList<User>();

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, "%"+name.toLowerCase()+"%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				users.add(new User(rs.getString("name"), rs.getString("email"), "password"));
			}

			LOGGER.info("User(s) with name like %s successfully listed.", name);
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = users;
	}
}
