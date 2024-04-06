--
-- PostgreSQL database dump
--

-- Dumped from database version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.11 (Ubuntu 14.11-0ubuntu0.22.04.1)

-- Started on 2024-04-06 17:48:18 CEST

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
-- TOC entry 210 (class 1259 OID 17502)
-- Name: authors; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.authors (
    id integer NOT NULL,
    penname character varying(60) NOT NULL,
    biography text
);


ALTER TABLE public.authors OWNER TO bookrec;

--
-- TOC entry 209 (class 1259 OID 17501)
-- Name: authors_id_seq; Type: SEQUENCE; Schema: public; Owner: bookrec
--

CREATE SEQUENCE public.authors_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.authors_id_seq OWNER TO bookrec;

--
-- TOC entry 3435 (class 0 OID 0)
-- Dependencies: 209
-- Name: authors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bookrec
--

ALTER SEQUENCE public.authors_id_seq OWNED BY public.authors.id;


--
-- TOC entry 220 (class 1259 OID 17569)
-- Name: bookgenre; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.bookgenre (
    genre_name bigint NOT NULL,
    book_id bigint NOT NULL
);


ALTER TABLE public.bookgenre OWNER TO bookrec;

--
-- TOC entry 218 (class 1259 OID 17538)
-- Name: books; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.books (
    id integer NOT NULL,
    isbn character(13),
    title character varying(100) NOT NULL,
    plot text,
    cover bytea,
    publisher_id integer
);


ALTER TABLE public.books OWNER TO bookrec;

--
-- TOC entry 217 (class 1259 OID 17537)
-- Name: books_id_seq; Type: SEQUENCE; Schema: public; Owner: bookrec
--

CREATE SEQUENCE public.books_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.books_id_seq OWNER TO bookrec;

--
-- TOC entry 3436 (class 0 OID 0)
-- Dependencies: 217
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bookrec
--

ALTER SEQUENCE public.books_id_seq OWNED BY public.books.id;


--
-- TOC entry 214 (class 1259 OID 17522)
-- Name: genres; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.genres (
    id integer NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE public.genres OWNER TO bookrec;

--
-- TOC entry 213 (class 1259 OID 17521)
-- Name: genres_id_seq; Type: SEQUENCE; Schema: public; Owner: bookrec
--

CREATE SEQUENCE public.genres_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.genres_id_seq OWNER TO bookrec;

--
-- TOC entry 3437 (class 0 OID 0)
-- Dependencies: 213
-- Name: genres_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bookrec
--

ALTER SEQUENCE public.genres_id_seq OWNED BY public.genres.id;


--
-- TOC entry 219 (class 1259 OID 17553)
-- Name: hasread; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.hasread (
    book_id bigint NOT NULL,
    user_id bigint NOT NULL,
    rate smallint,
    CONSTRAINT rate_value CHECK (((rate >= 1) AND (rate <= 5)))
);


ALTER TABLE public.hasread OWNER TO bookrec;

--
-- TOC entry 216 (class 1259 OID 17531)
-- Name: publishers; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.publishers (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.publishers OWNER TO bookrec;

--
-- TOC entry 215 (class 1259 OID 17530)
-- Name: publishers_id_seq; Type: SEQUENCE; Schema: public; Owner: bookrec
--

CREATE SEQUENCE public.publishers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.publishers_id_seq OWNER TO bookrec;

--
-- TOC entry 3438 (class 0 OID 0)
-- Dependencies: 215
-- Name: publishers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bookrec
--

ALTER SEQUENCE public.publishers_id_seq OWNED BY public.publishers.id;


--
-- TOC entry 212 (class 1259 OID 17511)
-- Name: users; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    password character(60) NOT NULL
);


ALTER TABLE public.users OWNER TO bookrec;

--
-- TOC entry 211 (class 1259 OID 17510)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: bookrec
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO bookrec;

--
-- TOC entry 3439 (class 0 OID 0)
-- Dependencies: 211
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bookrec
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 221 (class 1259 OID 17584)
-- Name: write; Type: TABLE; Schema: public; Owner: bookrec
--

CREATE TABLE public.write (
    book_id bigint NOT NULL,
    author_id bigint NOT NULL,
    year date
);


ALTER TABLE public.write OWNER TO bookrec;

--
-- TOC entry 3241 (class 2604 OID 17505)
-- Name: authors id; Type: DEFAULT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.authors ALTER COLUMN id SET DEFAULT nextval('public.authors_id_seq'::regclass);


--
-- TOC entry 3245 (class 2604 OID 17541)
-- Name: books id; Type: DEFAULT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.books ALTER COLUMN id SET DEFAULT nextval('public.books_id_seq'::regclass);


--
-- TOC entry 3243 (class 2604 OID 17525)
-- Name: genres id; Type: DEFAULT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.genres ALTER COLUMN id SET DEFAULT nextval('public.genres_id_seq'::regclass);


--
-- TOC entry 3244 (class 2604 OID 17534)
-- Name: publishers id; Type: DEFAULT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.publishers ALTER COLUMN id SET DEFAULT nextval('public.publishers_id_seq'::regclass);


--
-- TOC entry 3242 (class 2604 OID 17514)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3418 (class 0 OID 17502)
-- Dependencies: 210
-- Data for Name: authors; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.authors (id, penname, biography) FROM stdin;
1	J.K. Rowling	Joanne Rowling, known as J.K. Rowling, is a British author famous for the Harry Potter book series.
2	Cassandra Clare	Cassandra Clare is an American author known for her Shadowhunters series, including The Mortal Instruments, The Infernal Devices, and The Dark Artifices.
\.


--
-- TOC entry 3428 (class 0 OID 17569)
-- Dependencies: 220
-- Data for Name: bookgenre; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.bookgenre (genre_name, book_id) FROM stdin;
1	1
10	1
7	1
1	2
10	2
7	2
1	3
10	3
7	3
1	4
10	4
7	4
1	5
10	5
7	5
1	6
10	6
7	6
1	7
10	7
7	7
24	8
4	8
7	8
10	8
24	9
4	9
7	9
10	9
24	10
4	10
7	10
10	10
24	11
4	11
7	11
10	11
24	12
4	12
7	12
10	12
24	13
4	13
7	13
10	13
\.


--
-- TOC entry 3426 (class 0 OID 17538)
-- Dependencies: 218
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.books (id, isbn, title, plot, cover, publisher_id) FROM stdin;
1	9780439554930	Harry Potter and the Sorcerer's Stone	Harry Potter discovers he is a wizard and begins his first year at Hogwarts School of Witchcraft and Wizardry.	\N	\N
2	9780439064866	Harry Potter and the Chamber of Secrets	Harry Potter returns to Hogwarts for his second year, but finds dark whispers within the castle walls.	\N	\N
3	9780439136365	Harry Potter and the Prisoner of Azkaban	Harry learns that Sirius Black, a convicted murderer, has escaped from Azkaban and may be after him.	\N	\N
4	9780439358071	Harry Potter and the Goblet of Fire	Harry competes in the Triwizard Tournament amidst rising tensions and dark forces.	\N	\N
5	9780439785969	Harry Potter and the Order of the Phoenix	Harry forms "Dumbledore's Army" to fight against the oppressive regime at Hogwarts and the rise of Voldemort.	\N	\N
6	9780439064873	Harry Potter and the Half-Blood Prince	Harry learns more about Voldemort's past and seeks to destroy his Horcruxes.	\N	\N
7	9780545010221	Harry Potter and the Deathly Hallows	Harry, Ron, and Hermione set out to destroy Voldemort's remaining Horcruxes and defeat him once and for all.	\N	\N
8	9781481456029	Shadowhunters - City of Bones	Fifteen-year-old Clary Fray discovers she comes from a long line of Shadowhunters, human-angel hybrids who hunt down demons.	\N	\N
9	9781481455992	Shadowhunters - City of Ashes	Clary delves deeper into the Shadow World as her relationships with Jace, Simon, and others become more complicated.	\N	\N
10	9781481456036	Shadowhunters - City of Glass	Clary must travel to the City of Glass to save her mother and discover the truth about her past.	\N	\N
11	9781481456005	Shadowhunters - City of Fallen Angels	Clary and her friends face a new threat as a dark force arises in the Shadowhunter world.	\N	\N
12	9781481455978	Shadowhunters - City of Lost Souls	Clary and her friends embark on a dangerous mission to save Simon and stop Sebastian.	\N	\N
13	9781481456012	Shadowhunters - City of Heavenly Fire	Clary and her allies must confront Sebastian and his dark plans to save the world from destruction.	\N	\N
\.


--
-- TOC entry 3422 (class 0 OID 17522)
-- Dependencies: 214
-- Data for Name: genres; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.genres (id, name) FROM stdin;
1	Fantasy
2	Science Fiction
3	Mystery
4	Romance
5	Thriller
6	Horror
7	Adventure
8	Historical Fiction
9	Literary Fiction
10	Young Adult
11	Biography
12	Autobiography
14	Self-Help
15	Business
16	Cookbook
17	Travel
18	History
19	Science
20	Psychology
22	Comedy
23	Drama
24	Urban Fantasy
25	Paranormal
\.


--
-- TOC entry 3427 (class 0 OID 17553)
-- Dependencies: 219
-- Data for Name: hasread; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.hasread (book_id, user_id, rate) FROM stdin;
1	1	\N
8	1	5
9	1	5
10	1	5
11	1	3
12	1	4
13	1	5
\.


--
-- TOC entry 3424 (class 0 OID 17531)
-- Dependencies: 216
-- Data for Name: publishers; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.publishers (id, name) FROM stdin;
\.


--
-- TOC entry 3420 (class 0 OID 17511)
-- Dependencies: 212
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.users (id, name, email, password) FROM stdin;
1	borto	stefano.bortolatto@studenti.unipd.it	12345                                                       
\.


--
-- TOC entry 3429 (class 0 OID 17584)
-- Dependencies: 221
-- Data for Name: write; Type: TABLE DATA; Schema: public; Owner: bookrec
--

COPY public.write (book_id, author_id, year) FROM stdin;
1	1	1997-01-01
2	1	1998-01-01
3	1	1999-01-01
4	1	2000-01-01
5	1	2003-01-01
6	1	2005-01-01
7	1	2007-01-01
8	2	2007-01-01
9	2	2008-01-01
10	2	2009-01-01
11	2	2011-01-01
12	2	2012-01-01
13	2	2014-01-01
\.


--
-- TOC entry 3440 (class 0 OID 0)
-- Dependencies: 209
-- Name: authors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bookrec
--

SELECT pg_catalog.setval('public.authors_id_seq', 2, true);


--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 217
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bookrec
--

SELECT pg_catalog.setval('public.books_id_seq', 13, true);


--
-- TOC entry 3442 (class 0 OID 0)
-- Dependencies: 213
-- Name: genres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bookrec
--

SELECT pg_catalog.setval('public.genres_id_seq', 25, true);


--
-- TOC entry 3443 (class 0 OID 0)
-- Dependencies: 215
-- Name: publishers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bookrec
--

SELECT pg_catalog.setval('public.publishers_id_seq', 1, false);


--
-- TOC entry 3444 (class 0 OID 0)
-- Dependencies: 211
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: bookrec
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- TOC entry 3248 (class 2606 OID 17509)
-- Name: authors authors_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (id);


--
-- TOC entry 3268 (class 2606 OID 17573)
-- Name: bookgenre bookgenre_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.bookgenre
    ADD CONSTRAINT bookgenre_pkey PRIMARY KEY (genre_name, book_id);


--
-- TOC entry 3262 (class 2606 OID 17547)
-- Name: books books_isbn_key; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_isbn_key UNIQUE (isbn);


--
-- TOC entry 3264 (class 2606 OID 17545)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- TOC entry 3256 (class 2606 OID 17529)
-- Name: genres genres_name_key; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_name_key UNIQUE (name);


--
-- TOC entry 3258 (class 2606 OID 17527)
-- Name: genres genres_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_pkey PRIMARY KEY (id);


--
-- TOC entry 3266 (class 2606 OID 17558)
-- Name: hasread hasread_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.hasread
    ADD CONSTRAINT hasread_pkey PRIMARY KEY (book_id, user_id);


--
-- TOC entry 3260 (class 2606 OID 17536)
-- Name: publishers publishers_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pkey PRIMARY KEY (id);


--
-- TOC entry 3250 (class 2606 OID 17520)
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- TOC entry 3252 (class 2606 OID 17518)
-- Name: users users_name_key; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_name_key UNIQUE (name);


--
-- TOC entry 3254 (class 2606 OID 17516)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3270 (class 2606 OID 17588)
-- Name: write write_pkey; Type: CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.write
    ADD CONSTRAINT write_pkey PRIMARY KEY (book_id, author_id);


--
-- TOC entry 3274 (class 2606 OID 17574)
-- Name: bookgenre bookgenre_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.bookgenre
    ADD CONSTRAINT bookgenre_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(id);


--
-- TOC entry 3275 (class 2606 OID 17579)
-- Name: bookgenre bookgenre_genre_name_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.bookgenre
    ADD CONSTRAINT bookgenre_genre_name_fkey FOREIGN KEY (genre_name) REFERENCES public.genres(id);


--
-- TOC entry 3271 (class 2606 OID 17548)
-- Name: books books_publisher_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_publisher_id_fkey FOREIGN KEY (publisher_id) REFERENCES public.publishers(id);


--
-- TOC entry 3272 (class 2606 OID 17559)
-- Name: hasread hasread_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.hasread
    ADD CONSTRAINT hasread_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(id);


--
-- TOC entry 3273 (class 2606 OID 17564)
-- Name: hasread hasread_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.hasread
    ADD CONSTRAINT hasread_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3277 (class 2606 OID 17594)
-- Name: write write_author_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.write
    ADD CONSTRAINT write_author_id_fkey FOREIGN KEY (author_id) REFERENCES public.authors(id);


--
-- TOC entry 3276 (class 2606 OID 17589)
-- Name: write write_book_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: bookrec
--

ALTER TABLE ONLY public.write
    ADD CONSTRAINT write_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(id);


-- Completed on 2024-04-06 17:48:18 CEST

--
-- PostgreSQL database dump complete
--

