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

package dei.webapp.resource;

//import java.sql.Blob;

/**
 * Represents the data about an book.
 * 
 * @version 1.00
 * @since 1.00
 */
public class Book {

	/**
	 * The isbn (identifier) of the book
	 */
	private final String isbn;

	/**
	 * The title of the book
	 */
	private final String title;

	/**
	 * The plot of the book
	 */
	private final String plot;
	
	/**
	 * The release date of the book
	 */
	private final String release;
	
	/**
	 * The publisher of the book
	 */
	private final String publisher_name;
	
	/**
	 * The cover of the book
	 */
	private final byte[] cover;
	
	/**
	 * The cover type of the book
	 */
	private final String coverExt;

	/**
	 * Constructor for the Book class.
	 *
	 * @param isbn           the ISBN of the book
	 * @param title          the title of the book
	 * @param plot           the plot summary of the book
	 * @param cover          the cover image of the book
	 * @param release        the release date of the book
	 * @param publisher_name the name of the publisher
	 */
	public Book(final String isbn, final String title, final String plot, final byte[] cover,
	            final String coverExt, final String release, final String publisher_name) {
		this.isbn = isbn;
		this.title = title;
		this.plot = plot;
		this.release = release;
		this.publisher_name = publisher_name;
		this.cover = cover;
		this.coverExt = coverExt;
	}

	/**
	 * Returns the isbn of the book.
	 * 
	 * @return the isbn of the book.
	 */
	public final String getIsbn() {
		return isbn;
	}

	/**
	 * Returns the title of the book.
	 * 
	 * @return the title of the book.
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * Returns the plot of the book.
	 * 
	 * @return the plot of the book.
	 */
	public final String getPlot() {
		return plot;
	}
	
	/**
	 * Returns the release date of the book.
	 * 
	 * @return the release date of the book.
	 */
	public final String getRelease() {
		return release;
	}

	/**
	 * Returns the publisher of the book.
	 * 
	 * @return the publisher of the book.
	 */
	public final String getPublisher_name() {
		return publisher_name;
	}
	
	/**
	 * Returns the cover of the book.
	 * 
	 * @return the cover of the book.
	 */
    public final byte[] getCover() {
        return cover;
    }
    
    /**
	 * Returns the cover type of the book.
	 * 
	 * @return the cover type of the book.
	 */
    public final String getCoverType() {
    	if (coverExt == ".jpg" || coverExt == "jpg" || coverExt == ".jpeg" || coverExt == "jpeg")
    	{
    		return "image/jpeg";
    	}
    	else if (coverExt == ".png" || coverExt == "png")
    	{
    		return "image/png";
    	}
    	else
    	{
    		return "text/plain";
    	}
    }

}
