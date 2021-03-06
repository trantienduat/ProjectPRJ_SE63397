USE [master]
GO
/****** Object:  Database [ProjectPRJ]    Script Date: 11/2/2018 6:06:35 PM ******/
CREATE DATABASE [ProjectPRJ]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ProjectPRJ', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ProjectPRJ.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'ProjectPRJ_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ProjectPRJ_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [ProjectPRJ] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ProjectPRJ].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ProjectPRJ] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ProjectPRJ] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ProjectPRJ] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ProjectPRJ] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ProjectPRJ] SET ARITHABORT OFF 
GO
ALTER DATABASE [ProjectPRJ] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ProjectPRJ] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ProjectPRJ] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ProjectPRJ] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ProjectPRJ] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ProjectPRJ] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ProjectPRJ] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ProjectPRJ] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ProjectPRJ] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ProjectPRJ] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ProjectPRJ] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ProjectPRJ] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ProjectPRJ] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ProjectPRJ] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ProjectPRJ] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ProjectPRJ] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ProjectPRJ] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ProjectPRJ] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ProjectPRJ] SET  MULTI_USER 
GO
ALTER DATABASE [ProjectPRJ] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ProjectPRJ] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ProjectPRJ] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ProjectPRJ] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [ProjectPRJ] SET DELAYED_DURABILITY = DISABLED 
GO
USE [ProjectPRJ]
GO
/****** Object:  Table [dbo].[tbl_account]    Script Date: 11/2/2018 6:06:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_account](
	[username] [varchar](20) NOT NULL,
	[password] [varchar](30) NOT NULL,
	[custID] [varchar](10) NOT NULL,
 CONSTRAINT [PK_tbl_account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_customer]    Script Date: 11/2/2018 6:06:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_customer](
	[custID] [varchar](10) NOT NULL,
	[lastName] [varchar](15) NOT NULL,
	[middleName] [varchar](30) NULL,
	[firstName] [varchar](15) NOT NULL,
	[address] [varchar](250) NOT NULL,
	[phone] [varchar](11) NOT NULL,
	[custLevel] [int] NOT NULL,
 CONSTRAINT [PK_tbl_customer] PRIMARY KEY CLUSTERED 
(
	[custID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_order]    Script Date: 11/2/2018 6:06:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_order](
	[orderID] [varchar](10) NOT NULL,
	[orderDate] [datetime] NOT NULL,
	[custID] [varchar](10) NOT NULL,
	[total] [float] NOT NULL,
 CONSTRAINT [PK_tbl_order] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_orderDetail]    Script Date: 11/2/2018 6:06:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_orderDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[productID] [varchar](10) NOT NULL,
	[quantity] [int] NOT NULL,
	[unitPrice] [float] NOT NULL,
	[total] [float] NOT NULL,
	[orderID] [varchar](10) NOT NULL,
	[size] [varchar](3) NOT NULL,
 CONSTRAINT [PK_tbl_orderDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_shoes]    Script Date: 11/2/2018 6:06:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_shoes](
	[shoesID] [varchar](10) NOT NULL,
	[description] [varchar](50) NULL,
	[quantity] [int] NOT NULL,
 CONSTRAINT [PK_tbl_shoes] PRIMARY KEY CLUSTERED 
(
	[shoesID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_shoesDetail]    Script Date: 11/2/2018 6:06:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_shoesDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[shoesID] [varchar](10) NOT NULL,
	[sizeID] [varchar](3) NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
 CONSTRAINT [PK_tbl_shoesDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_sizes]    Script Date: 11/2/2018 6:06:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_sizes](
	[id] [varchar](3) NOT NULL,
	[sizes] [int] NOT NULL,
	[country] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tbl_sizes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tbl_account] ([username], [password], [custID]) VALUES (N'a', N'a', N'cust02')
INSERT [dbo].[tbl_account] ([username], [password], [custID]) VALUES (N'admin', N'admin', N'cust01')
INSERT [dbo].[tbl_account] ([username], [password], [custID]) VALUES (N'guest', N'guest', N'cust03')
INSERT [dbo].[tbl_customer] ([custID], [lastName], [middleName], [firstName], [address], [phone], [custLevel]) VALUES (N'cust01', N'Duat', N'Tien', N'Tran', N'Khanh Hoa', N'0337779773', 5)
INSERT [dbo].[tbl_customer] ([custID], [lastName], [middleName], [firstName], [address], [phone], [custLevel]) VALUES (N'cust02', N'A', N'Van', N'Nguyen', N'TPHCM', N'0123456425', 1)
INSERT [dbo].[tbl_customer] ([custID], [lastName], [middleName], [firstName], [address], [phone], [custLevel]) VALUES (N'cust03', N'guest', N'guest', N'guest', N'Dong Nai', N'01546', 2)
INSERT [dbo].[tbl_shoes] ([shoesID], [description], [quantity]) VALUES (N'AUB', N'Adidas Ultra Boost', 200)
INSERT [dbo].[tbl_shoes] ([shoesID], [description], [quantity]) VALUES (N'BHX', N'Bitis HunterX', 200)
INSERT [dbo].[tbl_shoes] ([shoesID], [description], [quantity]) VALUES (N'MNHPD', N'Mens Nike HyperDunk', 200)
INSERT [dbo].[tbl_shoes] ([shoesID], [description], [quantity]) VALUES (N'NMA', N'Nike Max Air', 200)
INSERT [dbo].[tbl_shoes] ([shoesID], [description], [quantity]) VALUES (N'PTP', N'Peak Tony Parker 4', 200)
SET IDENTITY_INSERT [dbo].[tbl_shoesDetail] ON 

INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (1, N'AUB', N'M', 50, 150)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (2, N'AUB', N'L', 50, 170)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (3, N'AUB', N'XL', 50, 190)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (4, N'AUB', N'XXL', 50, 210)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (5, N'BHX', N'M', 50, 35)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (6, N'BHX', N'L', 20, 45)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (7, N'BHX', N'XL', 80, 55)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (8, N'BHX', N'XXL', 100, 65)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (9, N'NMA', N'XXL', 100, 200)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (10, N'NMA', N'XL', 100, 180)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (11, N'MNHPD', N'XL', 150, 220)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (12, N'MNHPD', N'XXL', 50, 250)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (13, N'PTP', N'M', 80, 80)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (14, N'PTP', N'L', 80, 85)
INSERT [dbo].[tbl_shoesDetail] ([id], [shoesID], [sizeID], [quantity], [price]) VALUES (15, N'PTP', N'XL', 40, 90)
SET IDENTITY_INSERT [dbo].[tbl_shoesDetail] OFF
INSERT [dbo].[tbl_sizes] ([id], [sizes], [country]) VALUES (N'L', 36, N'VietNam')
INSERT [dbo].[tbl_sizes] ([id], [sizes], [country]) VALUES (N'M', 32, N'VietNam')
INSERT [dbo].[tbl_sizes] ([id], [sizes], [country]) VALUES (N'S', 28, N'VietNam')
INSERT [dbo].[tbl_sizes] ([id], [sizes], [country]) VALUES (N'XL', 40, N'VietNam')
INSERT [dbo].[tbl_sizes] ([id], [sizes], [country]) VALUES (N'XXL', 44, N'VietNam')
SET ANSI_PADDING ON

GO
/****** Object:  Index [uq_tblAccount]    Script Date: 11/2/2018 6:06:35 PM ******/
ALTER TABLE [dbo].[tbl_account] ADD  CONSTRAINT [uq_tblAccount] UNIQUE NONCLUSTERED 
(
	[custID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[tbl_order] ADD  CONSTRAINT [DF_tbl_order_orderDate]  DEFAULT (getdate()) FOR [orderDate]
GO
ALTER TABLE [dbo].[tbl_account]  WITH CHECK ADD  CONSTRAINT [FK_tbl_account_tbl_customer] FOREIGN KEY([custID])
REFERENCES [dbo].[tbl_customer] ([custID])
GO
ALTER TABLE [dbo].[tbl_account] CHECK CONSTRAINT [FK_tbl_account_tbl_customer]
GO
ALTER TABLE [dbo].[tbl_order]  WITH CHECK ADD  CONSTRAINT [FK_tbl_order_tbl_customer] FOREIGN KEY([custID])
REFERENCES [dbo].[tbl_customer] ([custID])
GO
ALTER TABLE [dbo].[tbl_order] CHECK CONSTRAINT [FK_tbl_order_tbl_customer]
GO
ALTER TABLE [dbo].[tbl_orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_orderDetail_tbl_order] FOREIGN KEY([orderID])
REFERENCES [dbo].[tbl_order] ([orderID])
GO
ALTER TABLE [dbo].[tbl_orderDetail] CHECK CONSTRAINT [FK_tbl_orderDetail_tbl_order]
GO
ALTER TABLE [dbo].[tbl_orderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_orderDetail_tbl_shoes] FOREIGN KEY([productID])
REFERENCES [dbo].[tbl_shoes] ([shoesID])
GO
ALTER TABLE [dbo].[tbl_orderDetail] CHECK CONSTRAINT [FK_tbl_orderDetail_tbl_shoes]
GO
ALTER TABLE [dbo].[tbl_shoesDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_shoesDetail_tbl_shoes] FOREIGN KEY([shoesID])
REFERENCES [dbo].[tbl_shoes] ([shoesID])
GO
ALTER TABLE [dbo].[tbl_shoesDetail] CHECK CONSTRAINT [FK_tbl_shoesDetail_tbl_shoes]
GO
ALTER TABLE [dbo].[tbl_shoesDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_shoesDetail_tbl_sizes] FOREIGN KEY([sizeID])
REFERENCES [dbo].[tbl_sizes] ([id])
GO
ALTER TABLE [dbo].[tbl_shoesDetail] CHECK CONSTRAINT [FK_tbl_shoesDetail_tbl_sizes]
GO
USE [master]
GO
ALTER DATABASE [ProjectPRJ] SET  READ_WRITE 
GO
