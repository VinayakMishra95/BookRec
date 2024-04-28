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

import unipd.webapp.project.resource.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Deletes an user from the database.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class DeleteUserDAO extends AbstractDAO<User> {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "DELETE FROM Public.User WHERE name = ? RETURNING *";

    /**
     * The username of the user
     */
    private final String name;

    /**
     * Creates a new object for deleting an user.
     *
     * @param con
     *            the connection to the database.
     * @param name
     *            the badge of the user.
     */
    public DeleteUserDAO(final Connection con, final String name) {
        super(con);
        this.name = name;
    }

    @Override
    protected final void doAccess() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the deleted user
        User e = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, "%"+name.toLowerCase()+"%");

            rs = pstmt.executeQuery();

            if (rs.next()) {
                e = new User(rs.getString("name"), "email@empty.com", "password");

                LOGGER.info("User %s successfully deleted from the database.", name);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

        }

        this.outputParam = e;
    }
}
