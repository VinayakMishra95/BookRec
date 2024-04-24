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

package unipd.webapp.project.database;

import unipd.webapp.project.resource.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches authors by their name.
 *
 * @version 1.00
 * @since 1.00
 */
public final class SearchAuthorDAO extends AbstractDAO<List<Author>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM Authors WHERE name LIKE '%?%'";

	/**
	 * The name of the author
	 */
	private final String name;

	/**
	 * Creates a new object for searching authors by name.
	 *
	 * @param con    the connection to the database.
	 * @param name the name of the author.
	 */
	public SearchAuthorDAO(final Connection con, final String name) {
		super(con);
		this.name = name;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Author> authors = new ArrayList<Author>();

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				authors.add(new Author(rs.getString("name"), rs.getString("biography")));
			}

			LOGGER.info("Author(s) with name like %s successfully listed.", name);
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = authors;
	}
}
