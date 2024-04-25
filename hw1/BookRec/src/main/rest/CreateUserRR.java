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

package it.unipd.dei.webapp.rest;

import it.unipd.dei.webapp.database.CreateUserDAO;
import it.unipd.dei.webapp.resource.Actions;
import it.unipd.dei.webapp.resource.User;
import it.unipd.dei.webapp.resource.LogContext;
import it.unipd.dei.webapp.resource.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.EOFException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A REST resource for creating {@link Employee}s.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class CreateUserRR extends AbstractRR {

    /**
     * Creates a new REST resource for creating {@code Employee}s.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public CreateUserRR(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(Actions.CREATE_EMPLOYEE, req, res, con);
    }


    @Override
    protected void doServe() throws IOException {

        User e = null;
        Message m = null;

        try {
            final User user = User.fromJSON(req.getInputStream());

            LogContext.setResource(Integer.toString(employee.getBadge()));

            // creates a new DAO for accessing the database and stores the employee
            e = new CreateUserDAO(con, user).access().getOutputParam();

            if (e != null) {
                LOGGER.info("user successfully created.");

                res.setStatus(HttpServletResponse.SC_CREATED);
                e.toJSON(res.getOutputStream());
            } else { // it should not happen
                LOGGER.error("Fatal error while creating employee.");

                m = new Message("Cannot create the user: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (EOFException ex) {
            LOGGER.warn("Cannot create the user: no user JSON object found in the request.", ex);

            m = new Message("Cannot create the user: no user JSON object found in the request.", "E4A8",
                    ex.getMessage());
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(res.getOutputStream());
        } catch (SQLException ex) {
            if ("23505".equals(ex.getSQLState())) {
                LOGGER.warn("Cannot create the user: it already exists.");

                m = new Message("Cannot create the user: it already exists.", "E5A2", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                LOGGER.error("Cannot create the user: unexpected database error.", ex);

                m = new Message("Cannot create the user: unexpected database error.", "E5A1", ex.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }


}
