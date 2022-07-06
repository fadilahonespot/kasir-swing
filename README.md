# kasir-swing

- create database with name aprilmart
- import query SQL

### Query SQL
```
create table tingkatan(
    id int primary key auto_increment,
    nama varchar(25)
);

create table users(
    username varchar(50) primary key,
    pass text,
    nama varchar(100),
    alamat text,
    email varchar(100),
    telp varchar(15),
    tingkatan_id int,
    deleted_at datetime default null,
	
    foreign key(tingkatan_id) references tingkatan(id)
);

create table supplier(
	kode varchar(25) primary key,
        nama varchar(100),
        alamat text,
        telp varchar(15),
        email varchar(60),
        deleted_at datetime default null
);

create table barang(
	kode varchar(25) primary key,
        nama varchar(225),
        harga bigint,
        kode_supplier varchar(25),
        stok int,
        deleted_at datetime default null,
        foreign key(kode_supplier) references supplier(kode)
);

create table transaksi(
    id int primary key auto_increment,
    order_number varchar(50),
    kode_barang varchar(25),
    quantity int,
    total bigint,
    tanggal datetime,
    username varchar(50),
    
    foreign key(username) references users(username),
    foreign key(kode_barang) references barang(kode)
);

insert into tingkatan(nama) values ('super_user'), ('kasir');
insert into users(username, pass, nama, email, tingkatan_id) values('admin', 'e10adc3949ba59abbe56e057f20f883e', 'Administrator', 'admin@mail.com', 1);
```

### Run Project
./src/FormLogin.java

### Login Credential
username: admin <br />
password: 123456
