create database poketra;
\c poketra;

create table unite(
    idUnite serial primary key,
    unite text 
);

create table categorie(
    idCategorie serial primary key,
    categorie text 
);

create table look(
    idLook serial primary key,
    look text 
);

create table taille(
    idTaille serial primary key,
    taille text,
    niveau int
);

create table metier(
    idMetier serial primary key,
    nomMetier text,
    th float
);

create table Grade (
    id serial primary key ,
    experience int ,
    augmentation int ,
    designation text
);

create table client(
    idClient serial primary key,
    nom text,
    genre int
);

create table employe(
    idEmploye serial primary key,
    nom text,
    date_embauche date,
    idMetier int,
    foreign key (idMetier) references metier(idMetier)
);

--MATIERE PREMIERE
create table mp(
    idMp serial primary key,
    nom text,
    pu float,
    idUnite int,
    foreign key (idUnite) references unite(idUnite)
);

create table achat_mp (
    idAchat serial primary key,
    daty text,
    idMp int,
    qte float,
    pu float,
    foreign key (idMp) references mp(idMp)
);

create table historique_mp(
    id serial primary key,
    daty text,
    idmp int,
    stock float,
    foreign key (idMp) references mp(idMp)
);

create table stock(
    idMp int,
    reste float,
    CONSTRAINT uq_idMp UNIQUE (idMp),
    foreign key (idMp) references mp(idMp)
);

--LOOK
create table mpLook(
    idLook int,
    idMp int,
    CONSTRAINT uq_idLook_idMp UNIQUE (idLook,idMp),
    foreign key (idLook) references look(idLook),
    foreign key (idMp) references mp(idMp)
);

--POKETRA
create table poketra(
    idPoketra serial primary key,
    idCategorie int,
    idLook int,
    nom text,
    etat int,
    CONSTRAINT uq_idLook_idCategorie_nom UNIQUE (idLook,idCategorie,nom),
    foreign key (idLook) references look(idLook),
    foreign key (idCategorie) references categorie(idCategorie)
);

create table MpPoketra(
    id serial primary key,
    idPoketra int,
    idMp int,
    CONSTRAINT uq_idPoketra_idMp UNIQUE (idPoketra,idMp),
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idMp) references mp(idMp)
);

create table QteMp(
    id serial primary key,
    idPoketra int,
    idTaille int,
    idMp int,
    qte float,
    CONSTRAINT uq_idPoketra_idMp_idTaille UNIQUE (idPoketra,idMp,idTaille),
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idTaille) references taille(idTaille),
    foreign key (idMp) references mp(idMp)
);

create table prixDeVente(
    id serial primary key,
    idPoketra int,
    idTaille int,
    prix float,
    CONSTRAINT uq_idPoketra_idTaille UNIQUE (idPoketra,idTaille),
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idTaille) references taille(idTaille)
);

create table metierPoketra(
    id serial primary key,
    idPoketra int,
    idMetier int,
    temp float,
    nb int,
    CONSTRAINT uq_idPoketra_idMetier UNIQUE (idPoketra,idMetier),
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idMetier) references metier(idMetier)
);

create table ventePoketra(
    idVentePoketra serial primary key,
    idPoketra int,
    idClient int,
    nb int,
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idClient) references client(idClient)
);

create table entree(
    identree serial primary key,
    daty text,
    idPoketra int,
    idTaille int,
    nb int,
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idTaille) references taille(idTaille)
);

create table sortie(
    idsortie serial primary key,
    daty text,
    idPoketra int,
    idTaille int,
    nb int,
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idTaille) references taille(idTaille)
);

create table stockPoketra(
    id serial primary key,
    idpoketra int,
    idTaille int,
    stock int,
    CONSTRAINT uqStock_idpoketra_idTaille UNIQUE (idpoketra,idtaille),
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idTaille) references taille(idTaille)
);

create table historique(
    id serial primary key,
    daty text,
    idPoketra int,
    idTaille int,
    stock int,
    foreign key (idPoketra) references poketra(idPoketra),
    foreign key (idTaille) references taille(idTaille)
);

--sortie mp
    create table sortie_mp(
        idSortie serial primary key,
        daty text,
        idmp int,
        qte float,
        source int,
        foreign key (idMp) references mp(idMp),
        foreign key (source) references entree(identree)
    );


---VIEW
    --Matiere premiere
    create or replace view  getMp as select m.idMp,m.nom,m.idUnite,m.pu,u.unite
        from Mp m
            join unite u on m.idUnite = u.idUnite;

    create or replace view  getStock as select s.*,m.nom,m.pu,m.unite
        from stock s
            join getMp m on m.idMp = s.idMp;

    --LOOK
    create or replace view  getMpLook as select mpl.idLook,l.look,mpl.idMp,m.nom,m.unite,m.pu
        from mpLook mpl
            join look l on l.idLook = mpl.idLook
            join getMp m on m.idMp = mpl.idMp;
    
    --Poketra
    create or replace view  getPoketra as select p.*,l.look,c.categorie
        from poketra p
            join look l on l.idLook = p.idLook
            join categorie c on c.idCategorie = p.idCategorie;

    create or replace view  recherche as select mpp.*,p.idCategorie,p.idLook,mp.nom
        from MpPoketra mpp
            join poketra p on p.idPoketra = mpp.idPoketra
            join mp on mp.idMp = mpp.idMp;

    create or replace view  getMpPoketra as select mpp.idPoketra,mpp.id,mp.idMp,mp.nom,mp.unite,mp.pu
        from MpPoketra mpp
            join getMp mp on mp.idMp = mpp.idMp;

    create or replace view  getQte as select q.*,p.nom,t.taille,t.niveau,mp.nom as matiere,mp.pu,p.categorie,p.look
        from QteMp q
            join getpoketra p on q.idPoketra = p.idPoketra
            join mp on q.idmp = mp.idmp
            join taille t on t.idTaille = q.idTaille;

    create or replace view  getPrix as select idpoketra,categorie,look,nom,idTaille,taille,niveau,sum(qte*pu) as prix from getqte group by idpoketra,nom,idTaille,taille,niveau,categorie,look;

    create or replace view  getMetierPoketra as select mp.idPoketra,p.categorie,p.look,p.nom,mp.idMetier,m.nomMetier,m.th,mp.temp,mp.nb
        from metierPoketra mp
            join getPoketra p on p.idpoketra=mp.idpoketra
            join metier m on m.idMetier = mp.idMetier;

    create or replace view  getPrixOeuvre as select idpoketra,categorie,look,nom,sum(th*temp*nb) as prix_main_d_oeuvre from getMetierPoketra group by idpoketra,categorie,look,nom;

    create or replace view  getVente as select v.*,c.nom,c.genre,p.look,p.categorie,p.nom as poketra
        from ventePoketra v
            join client c on c.idClient = v.idClient
            join getpoketra p on p.idpoketra = v.idpoketra;

    create or replace view  getNb as select idPoketra,categorie,look,poketra,genre,sum(nb) from getVente group by idpoketra,categorie,look,poketra,genre;

    --Employe
    create or replace view  getEmploye as select e.*,m.nomMetier,m.th
        from employe e
            join metier m on m.idMetier = e.idMetier;

    create view v_employe as select e.*,(SELECT EXTRACT(YEAR FROM age(NOW(), e.date_embauche))) AS experience from getEmploye e;
        

---INSERTION
    --unite
    insert into unite (unite) values ('Metre');
    insert into unite (unite) values ('Kg');
    insert into unite (unite) values ('Unite');
    --taille
    insert into taille (taille) values ('XL');
    insert into taille (taille) values ('L');
    insert into taille (taille) values ('M');
    --grade
    insert into grade(experience,augmentation,designation) values (0,1,'Ouvrier');
    insert into grade(experience,augmentation,designation) values (2,2,'Senior');
    insert into grade(experience,augmentation,designation) values (5,3,'Expert');

--SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = 'poketra';

