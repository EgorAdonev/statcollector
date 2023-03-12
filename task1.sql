-- by Egor Adonev
CREATE TABLE customer (
  `customerID` int(11) NOT NULL,
  `loginPass` varchar(50) DEFAULT NULL,
  `Email` varchar(200) DEFAULT NULL,
  `customerName` varchar(200) DEFAULT NULL,
  `customerLastName` varchar(200) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `accountBalance` int(11) DEFAULT NULL
)
-- deliver
CREATE TABLE `purchase` (
  `customerID` int(11) FOREIGN KEY references customer(customerID),
  `purchaseID` int(11) DEFAULT NULL
)

CREATE TABLE `orders` (
  `orderID` int(11) NOT NULL,
  `shopID` int(11) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  `orderStatus` varchar(100) DEFAULT NULL,
  `payment` varchar(100) DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  `orderAddress` varchar(500) DEFAULT NULL,
  `totalprice` float DEFAULT '0'
)

CREATE TABLE `orderproduct` (
  `orderID` int(11) DEFAULT NULL,
  `productID` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
);

CREATE TABLE `orderstatus_logs` (
  `orderID` int(11) DEFAULT NULL,
  `oldStatus` varchar(100) DEFAULT NULL,
  `newStatus` varchar(100) DEFAULT NULL,
  `changetime` datetime DEFAULT NULL
);

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `productName` varchar(200) DEFAULT NULL,
  `shopID` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `productAmount` int(11) DEFAULT NULL
)

CREATE TABLE `shops` (
  `ShopID` int(11) NOT NULL,
  `ShopName` varchar(200) DEFAULT NULL,
  `City` varchar(100) DEFAULT NULL,
  `Address` varchar(500) DEFAULT NULL,
  `PhoneNum` varchar(30) DEFAULT NULL,
  `Manager` varchar(200) DEFAULT NULL
)
CREATE TABLE 'category'(
  'categoryID' varchar(100) not null,
  'catDescription' ntext not null,
  --razmer kartinki v baitah (256kB) 256 * 1024 * 1024 = 268 435 456 bait = 9 znakov, 
  'images' varbinary(9) not null,
  'availability' boolean,
  'totalProductsCount' int not null,
  'hierarchyLevel' foreign key references categoryhierarchy(cus),
  'hierarchyParentId' foreign key references categoryhierarchy
)
CREATE TABLE categoryhierarchy {
  'hierarchyLevel' int not null,
  'hierarchyParentId' int not null 
}

ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerID`);
  add foreign key `customerID` references orders(`customerID`),purchase(`customerID`);
ALTER TABLE `orderproduct`
  ADD KEY `orderID` (`orderID`),
  ADD KEY `productID` (`productID`);
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderID`),
  ADD KEY `shopID` (`shopID`);
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`),
  ADD KEY `shopID` (`shopID`);
ALTER TABLE `shops`
  ADD PRIMARY KEY (`ShopID`);
ALTER TABLE `category`
  ADD PRIMARY KEY ('categoryID'),
  ADD KEY `hierarchyLevel` (`hierarchyLevel`);
  ADD KEY `hierarchyParentId` (`hierarchyParentId`);
ALTER TABLE `categoryhierarchy`
  ADD PRIMARY KEY (`hierarchyLevel`),
  ADD KEY `hierarchyParentId` (`hierarchyParentId`);