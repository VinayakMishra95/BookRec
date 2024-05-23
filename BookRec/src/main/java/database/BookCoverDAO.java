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

import dei.webapp.resource.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Search book's cover by their isbn.
 *
 * @version 1.00
 * @since 1.00
 */
public final class BookCoverDAO extends AbstractDAO<byte[]> {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "SELECT cover FROM Books WHERE isbn LIKE ?";

	/**
	 * The isbn code of the book
	 */
	private final String isbn;

	/**
	 * Creates a new object for searching books by isbn.
	 *
	 * @param con    the connection to the database.
	 * @param title the title of the book.
	 */
	public BookCoverDAO(final Connection con, final String isbn) {
		super(con);
		this.isbn = isbn;
	}

	@Override
	public final void doAccess() throws SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// the results of the search
		byte[] cover = null;
		
		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			    cover = rs.getBytes("cover");
			}
			LOGGER.info("Cover of book %s retrieved successfully.", isbn);
		} finally {
			if (rs != null) {
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}

		}

		this.outputParam = cover;
	}
}
