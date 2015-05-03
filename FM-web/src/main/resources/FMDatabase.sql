
CREATE TABLE public.Contact_purpose (
                ID INTEGER NOT NULL,
                Name VARCHAR NOT NULL,
                Description VARCHAR NOT NULL,
                CONSTRAINT contactpurposepk PRIMARY KEY (ID)
);


CREATE SEQUENCE public.category_id_seq;

CREATE TABLE public.Category (
                ID INTEGER NOT NULL DEFAULT nextval('public.category_id_seq'),
                Name VARCHAR NOT NULL,
                Parent_Category_ID INTEGER NOT NULL,
                CONSTRAINT categorypk PRIMARY KEY (ID)
);


ALTER SEQUENCE public.category_id_seq OWNED BY public.Category.ID;

CREATE SEQUENCE public.country_id_seq;

CREATE TABLE public.Country (
                ID INTEGER NOT NULL DEFAULT nextval('public.country_id_seq'),
                Name VARCHAR NOT NULL,
                CONSTRAINT countrypk PRIMARY KEY (ID)
);


ALTER SEQUENCE public.country_id_seq OWNED BY public.Country.ID;

CREATE TABLE public.City (
                ID INTEGER NOT NULL,
                Name VARCHAR NOT NULL,
                Country_ID INTEGER NOT NULL,
                CONSTRAINT citypk PRIMARY KEY (ID)
);


CREATE TABLE public.Address (
                Id INTEGER NOT NULL,
                House_number VARCHAR NOT NULL,
                Street VARCHAR NOT NULL,
                Postal_code VARCHAR NOT NULL,
                City_ID INTEGER NOT NULL,
                CONSTRAINT addresspk PRIMARY KEY (Id)
);


CREATE TABLE public.Facility (
                ID INTEGER NOT NULL,
                Name VARCHAR NOT NULL,
                Description VARCHAR NOT NULL,
                Website VARCHAR NOT NULL,
                CONSTRAINT facilitypk PRIMARY KEY (ID)
);


CREATE TABLE public.Facility_Address (
                ID INTEGER NOT NULL,
                Facility_ID INTEGER NOT NULL,
                Address_ID INTEGER NOT NULL,
                CONSTRAINT facilityaddresspk PRIMARY KEY (ID)
);


CREATE TABLE public.Email (
                ID INTEGER NOT NULL,
                Address VARCHAR NOT NULL,
                Facility_ID INTEGER NOT NULL,
                Contact_purpose_ID INTEGER NOT NULL,
                CONSTRAINT emailpk PRIMARY KEY (ID)
);


CREATE TABLE public.Facility_Category (
                ID INTEGER NOT NULL,
                Facility_ID INTEGER NOT NULL,
                Category_ID INTEGER NOT NULL,
                CONSTRAINT facilitycategorypk PRIMARY KEY (ID)
);


CREATE TABLE public.Phone (
                ID INTEGER NOT NULL,
                Number VARCHAR NOT NULL,
                Type VARCHAR NOT NULL,
                Facility_ID INTEGER NOT NULL,
                Contact_purpose_ID INTEGER NOT NULL,
                CONSTRAINT phonepk PRIMARY KEY (ID)
);


ALTER TABLE public.Phone ADD CONSTRAINT contact_purpose_phone_fk
FOREIGN KEY (Contact_purpose_ID)
REFERENCES public.Contact_purpose (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Email ADD CONSTRAINT contact_purpose_email_fk
FOREIGN KEY (Contact_purpose_ID)
REFERENCES public.Contact_purpose (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Facility_Category ADD CONSTRAINT category_facility_category_fk
FOREIGN KEY (Category_ID)
REFERENCES public.Category (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Category ADD CONSTRAINT category_category_fk
FOREIGN KEY (Parent_Category_ID)
REFERENCES public.Category (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.City ADD CONSTRAINT country_city_fk
FOREIGN KEY (Country_ID)
REFERENCES public.Country (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Address ADD CONSTRAINT city_address_fk
FOREIGN KEY (City_ID)
REFERENCES public.City (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Facility_Address ADD CONSTRAINT address_facility_address_fk
FOREIGN KEY (Address_ID)
REFERENCES public.Address (Id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Phone ADD CONSTRAINT facility_contact_fk
FOREIGN KEY (Facility_ID)
REFERENCES public.Facility (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Facility_Category ADD CONSTRAINT facility_facility_category_fk
FOREIGN KEY (Facility_ID)
REFERENCES public.Facility (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Email ADD CONSTRAINT facility_email_fk
FOREIGN KEY (Facility_ID)
REFERENCES public.Facility (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Facility_Address ADD CONSTRAINT facility_facility_address_fk
FOREIGN KEY (Facility_ID)
REFERENCES public.Facility (ID)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
