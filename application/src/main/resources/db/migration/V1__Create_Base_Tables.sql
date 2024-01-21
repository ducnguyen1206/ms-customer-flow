CREATE TABLE IF NOT EXISTS tbl_product(
    id bigserial NOT NULL PRIMARY KEY,
    product_name varchar(255) NOT NULL,
    price NUMERIC NOT NULL,
    currency varchar(255) NOT NULL,
    category_id int8 NOT NULL,
    default_quantity int4 NOT NULL,
    description varchar(255),
    created_date timestamp NOT NULL,
    created_by varchar(255) NOT NULL,
    last_updated_date timestamp,
    last_updated_by varchar(255)
);

CREATE TABLE IF NOT EXISTS tbl_category(
    id bigserial NOT NULL PRIMARY KEY,
    category_name varchar(255) NOT NULL,
    created_date timestamp NOT NULL,
    created_by varchar(255) NOT NULL,
    last_updated_date timestamp,
    last_updated_by varchar(255)
);

CREATE TABLE IF NOT EXISTS tbl_branch(
    id bigserial NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    branch_code int4 NOT NULL,
    created_date timestamp NOT NULL,
    created_by varchar(255) NOT NULL,
    last_updated_date timestamp,
    last_updated_by varchar(255)
);

CREATE TABLE IF NOT EXISTS tbl_product_branch(
    id bigserial NOT NULL PRIMARY KEY,
    product_id int8 NOT NULL,
    branch_id int8 NOT NULL,
    status varchar(255) NOT NULL,
    created_date timestamp NOT NULL,
    created_by varchar(255) NOT NULL,
    last_updated_date timestamp,
    last_updated_by varchar(255)
);

CREATE TABLE IF NOT EXISTS tbl_reservation(
    id bigserial NOT NULL PRIMARY KEY,
    customer_name varchar(255) NOT NULL,
    phone_no varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    reservation_date timestamp NOT NULL,
    guest_no varchar(255) NOT NULL,
    branch_id int8 NOT NULL,
    user_id int8 NULL,
    notes varchar(255) NULL,
    status varchar(255) NOT NULL,
    created_date timestamp NOT NULL,
    created_by varchar(255) NOT NULL,
    last_updated_date timestamp,
    last_updated_by varchar(255)
);