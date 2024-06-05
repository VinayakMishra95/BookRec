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

import dei.webapp.database.ListUserDAO;
import dei.webapp.resource.Actions;
import dei.webapp.resource.User;
import dei.webapp.resource.Message;
import dei.webapp.resource.ResourceList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * A REST unipd.webapp.project.resource for listing {@link User}s.
 *
 * @version 1.00
 * @since 1.00
 */
public final class ListUserRR extends AbstractRR {

    /**
     * Creates a new REST unipd.webapp.project.resource for listing {@code User}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the unipd.webapp.project.database.
     */
    public ListUserRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(Actions.LIST_USER, req, res, con);
    }


    @Override
    protected void doServe() throws IOException {

        List<User> el = null;
        Message m = null;

        try {

            // creates a new DAO for accessing the unipd.webapp.project.database and lists the user(s)
            el = new ListUserDAO(con).access().getOutputParam();

            if (el != null) {
                LOGGER.info("User(s) successfully listed.");

                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(el).toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while listing user(s).");

                m = new Message("Cannot list user(s): unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (SQLException ex) {
            LOGGER.error("Cannot list user(s): unexpected unipd.webapp.project.database error.", ex);

            m = new Message("Cannot list user(s): unexpected unipd.webapp.project.database error.", "E5A1", ex.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }


}
