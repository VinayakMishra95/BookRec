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

import unipd.webapp.project.resource.Book;

import java.sql.Connection;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Searches books by their title.
 *
 * @version 1.00
 * @since 1.00
 */
public final class SearchBookDAO extends AbstractDAO<List<Book>> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT * FROM Books WHERE LOWER(title) LIKE ?";

	/**
	 * The title of the book
	 */
	private final String title;

	/**
	 * Creates a new object for searching books by title.
	 *
	 * @param con    the connection to the database.
	 * @param title the title of the book.
	 */
	public SearchBookDAO(final Connection con, final String title) {
		super(con);
		this.title = title;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		final List<Book> books = new ArrayList<Book>();

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, "%"+title.toLowerCase()+"%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
			    //Blob cover_blob = rs.getBlob("cover");
			    //byte[] cover = cover_blob.getBytes(1, (int) cover_blob.length());
				books.add(new Book(rs.getString("isbn"), rs.getString("title"), rs.getString("plot"),
				            rs.getBlob("cover"), rs.getString("release"), rs.getString("publisher_name")));
			}

			LOGGER.info("Book(s) with title like %s successfully listed.", title);
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = books;
	}
}
