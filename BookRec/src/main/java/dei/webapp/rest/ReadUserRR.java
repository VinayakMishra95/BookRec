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

package dei.webapp.rest;

import dei.webapp.database.ReadUserDAO;
import dei.webapp.resource.Actions;
import dei.webapp.resource.User;
import dei.webapp.resource.LogContext;
import dei.webapp.resource.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A REST resource for reading {@link User}s.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class ReadUserRR extends AbstractRR {

    /**
     * Creates a new REST resource for reading {@code User}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public ReadUserRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(Actions.READ_USER, req, res, con);
    }


    @Override
    protected void doServe() throws IOException {

        User e = null;
        Message m = null;

        try {
            // parse the URI path to extract the badge
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("user") + 8);

            final String name = path.substring(1);

            LogContext.setResource(name);

            // creates a new DAO for accessing the database and reads the User
            e = new ReadUserDAO(con, name).access().getOutputParam();

            if (e != null) {
                LOGGER.info("User successfully read.");

                res.setStatus(HttpServletResponse.SC_OK);
                e.toJSON(res.getOutputStream());
            } else {
                LOGGER.warn("user not found. Cannot read it.");

                m = new Message(String.format("user %d not found. Cannot read it.", name), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            LOGGER.warn("Cannot read the user: wrong format for URI /user/name", ex);

            m = new Message("Cannot read the user: wrong format for URI /user/name", "E4A7",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            LOGGER.error("Cannot read the user: unexpected database error.", ex);

            m = new Message("Cannot read the user: unexpected database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

}
