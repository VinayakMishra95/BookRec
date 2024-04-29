
package unipd.webapp.project.database;

import unipd.webapp.project.resource.User;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a new user into the unipd.webapp.project.database.
 * 
 * @version 1.00
 * @since 1.00
 */
public final class CreateUserDAO extends AbstractDAO {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "INSERT INTO users (name, email, password) VALUES (?, ?, md5(?))";

	/**
	/**
	 * The user to be stored into the unipd.webapp.project.database
	 */
	private final User user;

	/**
	 * Creates a new object for storing an user into the unipd.webapp.project.database.
	 * 
	 * @param con
	 *            the connection to the unipd.webapp.project.database.
	 * @param user
	 *            the user to be stored into the unipd.webapp.project.database.
	 */
	public CreateUserDAO(final Connection con, final User user) {
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

		try {
			pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());

			pstmt.execute();

			LOGGER.info("User %s successfully stored in the unipd.webapp.project.database.", user.getName());
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
		}

	}
}
