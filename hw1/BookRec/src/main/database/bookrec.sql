--
-- PostgreSQL unipd.webapp.project.database dump
--

-- Dumped from unipd.webapp.project.database version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: authors; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.authors (
    name character varying(100) NOT NULL,
    biography text
);


ALTER TABLE public.authors OWNER TO bookrec;

--
-- Name: bookgenre; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.bookgenre (
    genre_name character varying(50) NOT NULL,
    book_isbn character(13) NOT NULL
);


ALTER TABLE public.bookgenre OWNER TO bookrec;

--
-- Name: books; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.books (
    isbn character(13) NOT NULL,
    title character varying(100) NOT NULL,
    plot text,
    cover bytea,
    release date,
    publisher_name character varying(100)
);


ALTER TABLE public.books OWNER TO bookrec;

--
-- Name: genres; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.genres (
    name character varying(50) NOT NULL
);


ALTER TABLE public.genres OWNER TO bookrec;

--
-- Name: hasread; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.hasread (
    book_isbn character(13) NOT NULL,
    user_mail character varying(50) NOT NULL,
    rate smallint,
    CONSTRAINT rate_value CHECK (((rate >= 1) AND (rate <= 5)))
);


ALTER TABLE public.hasread OWNER TO bookrec;

--
-- Name: publishers; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.publishers (
    name character varying(100) NOT NULL
);


ALTER TABLE public.publishers OWNER TO bookrec;

--
-- Name: users; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.users (
    name character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    password character(32) NOT NULL
);


ALTER TABLE public.users OWNER TO bookrec;

--
-- Name: wrote; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.wrote (
    book_isbn character(13) NOT NULL,
    author_name character varying(100) NOT NULL
);


ALTER TABLE public.wrote OWNER TO bookrec;

--
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.authors (name, biography) FROM stdin;
J.K. Rowling	Joanne Rowling, better known by her pen name J.K. Rowling, is a British author, philanthropist, and film producer.
J.R.R. Tolkien	John Ronald Reuel Tolkien was an English writer, poet, philologist, and academic, best known as the author of the high fantasy works The Hobbit and The Lord of the Rings.
\.


--
-- Data for Name: bookgenre; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.bookgenre (genre_name, book_isbn) FROM stdin;
\.


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.books (isbn, title, plot, cover, release, publisher_name) FROM stdin;
9780747532743	Harry Potter and the Philosopher's Stone	The story follows Harry Potter, a young wizard who discovers his magical heritage on his eleventh birthday, when he receives a letter of acceptance to Hogwarts School of Witchcraft and Wizardry.	\N	1997-06-26	Bloomsbury Publishing
9780747538493	Harry Potter and the Chamber of Secrets	The plot follows Harry's second year at Hogwarts School of Witchcraft and Wizardry, during which a series of messages on the walls of the school's corridors warn that the "Chamber of Secrets" has been opened and that the "heir of Slytherin" would kill all pupils who do not come from all-magical families.	\N	1998-07-02	Bloomsbury Publishing
9780747546290	Harry Potter and the Prisoner of Azkaban	The novel follows Harry Potter, a young wizard, in his third year at Hogwarts School of Witchcraft and Wizardry.	\N	1999-07-08	Bloomsbury Publishing
9780747549550	Harry Potter and the Goblet of Fire	The story follows Harry Potter's fourth year at Hogwarts School of Witchcraft and Wizardry, along with the Triwizard Tournament.	\N	2000-07-08	Bloomsbury Publishing
9780747574491	Harry Potter and the Order of the Phoenix	The plot follows Harry Potter's fifth year at Hogwarts School of Witchcraft and Wizardry as the Ministry of Magic is in denial of Lord Voldemort's return.	\N	2003-06-21	Bloomsbury Publishing
9780747581086	Harry Potter and the Half-Blood Prince	The story follows Harry Potter's sixth year at Hogwarts School of Witchcraft and Wizardry as Lord Voldemort and his Death Eaters wreak havoc.	\N	2005-07-16	Bloomsbury Publishing
9780747591054	Harry Potter and the Deathly Hallows	The plot follows Harry Potter's quest to find and destroy Lord Voldemort's Horcruxes in order to stop him once and for all.	\N	2007-07-21	Bloomsbury Publishing
9780544003415	The Fellowship of the Ring	The first of three volumes of the epic novel The Lord of the Rings by the English author J.R.R. Tolkien.	\N	1954-07-29	Allen & Unwin
9780544003521	The Two Towers	The second volume of J.R.R. Tolkien's high fantasy novel The Lord of the Rings.	\N	1954-11-11	Allen & Unwin
9780544004030	The Return of the King	The third and final volume of J. R. R. Tolkien's The Lord of the Rings, following The Fellowship of the Ring and The Two Towers.	\N	1955-10-20	Allen & Unwin
\.


--
-- Data for Name: genres; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.genres (name) FROM stdin;
Fantasy
Science Fiction
Mystery
Romance
Thriller
\.


--
-- Data for Name: hasread; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.hasread (book_isbn, user_mail, rate) FROM stdin;
\.


--
-- Data for Name: publishers; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.publishers (name) FROM stdin;
Bloomsbury Publishing
Allen & Unwin
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.users (name, email, password) FROM stdin;
\.


--
-- Data for Name: wrote; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.wrote (book_isbn, author_name) FROM stdin;
9780747532743	J.K. Rowling
9780544003415	J.R.R. Tolkien
9780544003521	J.R.R. Tolkien
9780544004030	J.R.R. Tolkien
\.


--
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (name);


--
-- Name: bookgenre bookgenre_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.bookgenre
    ADD CONSTRAINT bookgenre_pkey PRIMARY KEY (genre_name, book_isbn);


--
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (isbn);


--
-- Name: genres genres_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_pkey PRIMARY KEY (name);


--
-- Name: hasread hasread_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.hasread
    ADD CONSTRAINT hasread_pkey PRIMARY KEY (book_isbn, user_mail);


--
-- Name: publishers publishers_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (name);


--
-- Name: users users_name_key; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_name_key UNIQUE (name);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (email);


--
-- Name: wrote wrote_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.wrote
    ADD CONSTRAINT wrote_pkey PRIMARY KEY (book_isbn, author_name);


--
-- Name: bookgenre bookgenre_book_isbn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.bookgenre
    ADD CONSTRAINT bookgenre_book_isbn_fkey FOREIGN KEY (book_isbn) REFERENCES public.books(isbn);


--
-- Name: bookgenre bookgenre_genre_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.bookgenre
    ADD CONSTRAINT bookgenre_genre_name_fkey FOREIGN KEY (genre_name) REFERENCES public.genres(name);


--
-- Name: books books_publisher_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_publisher_name_fkey FOREIGN KEY (publisher_name) REFERENCES public.publishers(name);


--
-- Name: hasread hasread_book_isbn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.hasread
    ADD CONSTRAINT hasread_book_isbn_fkey FOREIGN KEY (book_isbn) REFERENCES public.books(isbn);


--
-- Name: hasread hasread_user_mail_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.hasread
    ADD CONSTRAINT hasread_user_mail_fkey FOREIGN KEY (user_mail) REFERENCES public.users(email);


--
-- Name: wrote wrote_author_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.wrote
    ADD CONSTRAINT wrote_author_name_fkey FOREIGN KEY (author_name) REFERENCES public.authors(name);


--
-- Name: wrote wrote_book_isbn_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.wrote
    ADD CONSTRAINT wrote_book_isbn_fkey FOREIGN KEY (book_isbn) REFERENCES public.books(isbn);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT USAGE ON SCHEMA public TO bookrec;


--
-- PostgreSQL unipd.webapp.project.database dump complete
--

