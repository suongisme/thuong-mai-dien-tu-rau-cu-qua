use ecolife
go

create table Blog
(
    Id       int identity
        primary key,
    Image    nvarchar(max) not null,
    Title    nvarchar(max) not null,
    Content  nvarchar(max) not null,
    CreateAt datetime default getdate(),
    CreateBy nvarchar(max)
)
    go

create table Category
(
    Id           int identity
        primary key,
    Name         nvarchar(max) not null,
    Description  nvarchar(max) not null,
    DisplayOrder int default 0,
    Image        varchar(max)
    )
    go

create table Customer
(
    Id        int identity
        primary key,
    FirstName nvarchar(max) not null,
    LastName  nvarchar(max) not null,
    Email     nvarchar(max) not null,
    Phone     nvarchar(max) not null,
    Gender    bit,
    password  varchar(255)
)
    go

create table Orders
(
    Id          int identity
        primary key,
    CustomerId  int
        references Customer,
    TotalMoney  decimal,
    CreateAt    datetime default getdate(),
    OrderStatus int
)
    go

create table Product
(
    Id          int identity
        primary key,
    Name        nvarchar(max) not null,
    Price       decimal,
    Quantity    int,
    Type        nvarchar(max),
    Image       nvarchar(max),
    Weight      nvarchar(max),
    Dimensions  nvarchar(max),
    Materials   nvarchar(max),
    Description nvarchar(max),
    Status      bit,
    Rate        int,
    CategoryId  int
        references Category,
    discount    int
)
    go

create table OrderDetail
(
    OrderId   int
        references Orders,
    ProductId int
        references Product,
    Quantity  int not null,
    Price     decimal,
    id        int identity
        constraint OrderDetail_pk
        primary key
)
    go

create table Role
(
    Id          int identity
        primary key,
    Name        nvarchar(max) not null,
    Description nvarchar(max) not null,
    IsActive    bit           not null
)
    go

create table CustomerRole
(
    RoleId     int not null
        references Role,
    CustomerId int not null
        references Customer,
    id         int identity
        constraint CustomerRole_pk
        primary key
)
    go

