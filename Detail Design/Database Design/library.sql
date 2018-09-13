-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 16, 2018 lúc 01:33 AM
-- Phiên bản máy phục vụ: 10.1.28-MariaDB
-- Phiên bản PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `library`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `username` varchar(15) NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`username`, `password`) VALUES
('duytt12', '0a8d2aa244b826a75d66e90eceb3438a'),
('kientt97', 'f956e831dadf9199a066c8f5d4e33ac8'),
('kienvv2903', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhluv1', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhluv1sss', '55241e2483890c6886c42d2ee7278304'),
('linhluv2', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhluv3', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt1', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt2', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt3', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt4', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt5', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt6', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt7', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt8', 'f956e831dadf9199a066c8f5d4e33ac8'),
('linhtt97', 'f956e831dadf9199a066c8f5d4e33ac8');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `id` varchar(10) NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(30) NOT NULL,
  `idpub` varchar(10) NOT NULL,
  `idcat` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `book`
--

INSERT INTO `book` (`id`, `title`, `author`, `idpub`, `idcat`) VALUES
('EM1', 'Kinh tế lượng', 'Nguyễn Duy Dương', 'DHQG', 'EM'),
('EM2', 'Chứng khoán tuyển tập', 'Nguyễn Thùy Trang', 'DHQG', 'EM'),
('ET1', 'Điện tử tương tự số', 'Nguyễn Văn Thắng', 'BK', 'ET'),
('ET2', 'Máy điện', 'Nguyễn Văn Quang', 'BK', 'ET'),
('HH1', 'Hóa công', 'Nguyễn Thị Mỹ Hạnh', 'GD', 'HH'),
('HH2', 'Hóa hữu cơ', 'Nguyễn Thu Giang', 'GD', 'HH'),
('IT1', 'Kỹ thuật máy tính', 'Vũ Văn Kiên', 'DN', 'IT'),
('IT2', 'Cấu trúc dữ liệu và giải thuật', 'Nguyễn Ngọc Quang', 'SP', 'IT'),
('OL1', 'Vật lý đại cương 1', 'Nguyễn Duy Hòa', 'BK', 'OL'),
('OL2', 'Vật lý đại cương 2', 'Nguyễn Duy Hòa', 'BK', 'OL'),
('OL3', 'Đại Số ', 'Nguyễn Văn Phương', 'BK', 'OL');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bookcopy`
--

CREATE TABLE `bookcopy` (
  `idbook` varchar(10) NOT NULL,
  `idcopy` varchar(20) NOT NULL,
  `status` int(11) NOT NULL,
  `type` text NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `bookcopy`
--

INSERT INTO `bookcopy` (`idbook`, `idcopy`, `status`, `type`, `price`) VALUES
('EM1', 'EM1BCP1', 1, 'Có thể mượn', 25000),
('EM1', 'EM1BCP2', 1, 'Có thể mượn', 25000),
('EM1', 'EM1BCP3', 0, 'Có thể mượn', 52000),
('EM1', 'EM1BCP4', 0, 'Tham khảo', 52000),
('EM1', 'EM1BCP5', 0, 'Có thể mượn', 25000),
('EM1', 'EM1BCP6', 0, 'Có thể mượn', 25000),
('EM1', 'EM1BCP7', 0, 'Có thể mượn', 25000),
('EM1', 'EM1BCP8', 0, 'Có thể mượn', 25000),
('EM1', 'EM1BCP9', 0, 'Có thể mượn', 25000),
('EM2', 'EM2BCP1', 3, 'Có thể mượn', 19000),
('EM2', 'EM2BCP10', 0, 'Tham khảo', 25000),
('EM2', 'EM2BCP2', 0, 'Tham khảo', 19000),
('EM2', 'EM2BCP3', 0, 'Có thể mượn', 25000),
('EM2', 'EM2BCP4', 0, 'Có thể mượn', 25000),
('EM2', 'EM2BCP5', 0, 'Có thể mượn', 25000),
('EM2', 'EM2BCP6', 0, 'Có thể mượn', 25000),
('EM2', 'EM2BCP7', 0, 'Có thể mượn', 25000),
('EM2', 'EM2BCP8', 0, 'Có thể mượn', 25000),
('EM2', 'EM2BCP9', 0, 'Tham khảo', 25000),
('ET1', 'ET1BCP10', 3, 'Có thể mượn', 12000),
('ET1', 'ET1BCP11', 0, 'Có thể mượn', 12000),
('ET1', 'ET1BCP12', 0, 'Có thể mượn', 15000),
('ET1', 'ET1BCP13', 0, 'Có thể mượn', 15000),
('ET1', 'ET1BCP14', 0, 'Có thể mượn', 15000),
('ET1', 'ET1BCP2', 0, 'Tham khảo', 12000),
('ET1', 'ET1BCP4', 0, 'Có thể mượn', 11000),
('ET1', 'ET1BCP5', 0, 'Có thể mượn', 12000),
('ET1', 'ET1BCP6', 3, 'Có thể mượn', 12000),
('ET1', 'ET1BCP7', 0, 'Có thể mượn', 12000),
('ET1', 'ET1BCP8', 0, 'Có thể mượn', 12000),
('ET1', 'ET1BCP9', 0, 'Có thể mượn', 12000),
('ET2', 'ET2BCP1', 0, 'Tham khảo', 15000),
('ET2', 'ET2BCP10', 1, 'Có thể mượn', 12000),
('ET2', 'ET2BCP2', 1, 'Có thể mượn', 15000),
('ET2', 'ET2BCP3', 1, 'Có thể mượn', 15000),
('ET2', 'ET2BCP4', 0, 'Có thể mượn', 15000),
('ET2', 'ET2BCP5', 0, 'Có thể mượn', 15000),
('ET2', 'ET2BCP6', 0, 'Có thể mượn', 15000),
('ET2', 'ET2BCP7', 0, 'Có thể mượn', 15000),
('ET2', 'ET2BCP8', 0, 'Có thể mượn', 12000),
('ET2', 'ET2BCP9', 0, 'Có thể mượn', 12000),
('HH1', 'HH1BCP1', 0, 'Có thể mượn', 22000),
('HH2', 'HH2BCP1', 3, 'Có thể mượn', 12000),
('HH2', 'HH2BCP2', 3, 'Có thể mượn', 12000),
('HH2', 'HH2BCP3', 0, 'Có thể mượn', 12000),
('HH2', 'HH2BCP4', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP1', 1, 'Có thể mượn', 13000),
('IT1', 'IT1BCP10', 1, 'Có thể mượn', 12000),
('IT1', 'IT1BCP11', 1, 'Có thể mượn', 12000),
('IT1', 'IT1BCP12', 1, 'Có thể mượn', 12000),
('IT1', 'IT1BCP13', 1, 'Có thể mượn', 12000),
('IT1', 'IT1BCP14', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP15', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP16', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP17', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP18', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP19', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP2', 3, 'Có thể mượn', 13000),
('IT1', 'IT1BCP20', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP21', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP22', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP23', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP24', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP25', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP26', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP27', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP28', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP29', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP3', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP30', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP31', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP32', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP33', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP34', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP35', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP36', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP37', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP38', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP39', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP4', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP40', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP5', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP6', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP7', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP8', 0, 'Có thể mượn', 12000),
('IT1', 'IT1BCP9', 0, 'Có thể mượn', 12000),
('IT2', 'IT2BCP1', 0, 'Có thể mượn', 22000),
('IT2', 'IT2BCP2', 0, 'Có thể mượn', 29000),
('IT2', 'IT2BCP3', 0, 'Có thể mượn', 29000),
('IT2', 'IT2BCP4', 0, 'Có thể mượn', 29000),
('IT2', 'IT2BCP5', 0, 'Có thể mượn', 29000),
('IT2', 'IT2BCP6', 0, 'Có thể mượn', 29000),
('IT2', 'IT2BCP7', 0, 'Có thể mượn', 29000),
('OL1', 'OL1BCP1', 0, 'Tham khảo', 12000),
('OL1', 'OL1BCP2', 0, 'Có thể mượn', 12000),
('OL1', 'OL1BCP3', 0, 'Có thể mượn', 12000),
('OL1', 'OL1BCP4', 0, 'Có thể mượn', 12000),
('OL1', 'OL1BCP5', 0, 'Tham khảo', 12000),
('OL1', 'OL1BCP6', 0, 'Tham khảo', 12000),
('OL1', 'OL1BCP7', 0, 'Tham khảo', 12000),
('OL1', 'OL1BCP8', 0, 'Tham khảo', 12000),
('OL2', 'OL2BCP1', 3, 'Có thể mượn', 12000),
('OL2', 'OL2BCP10', 0, 'Có thể mượn', 12000),
('OL2', 'OL2BCP11', 0, 'Tham khảo', 12000),
('OL2', 'OL2BCP12', 0, 'Tham khảo', 12000),
('OL2', 'OL2BCP2', 0, 'Có thể mượn', 12000),
('OL2', 'OL2BCP3', 3, 'Có thể mượn', 12000),
('OL2', 'OL2BCP4', 3, 'Có thể mượn', 12000),
('OL2', 'OL2BCP5', 0, 'Có thể mượn', 12000),
('OL2', 'OL2BCP6', 0, 'Có thể mượn', 12000),
('OL2', 'OL2BCP7', 0, 'Có thể mượn', 12000),
('OL2', 'OL2BCP8', 0, 'Có thể mượn', 12000),
('OL2', 'OL2BCP9', 0, 'Có thể mượn', 12000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `borrowcard`
--

CREATE TABLE `borrowcard` (
  `id` varchar(45) NOT NULL,
  `cardno` varchar(16) NOT NULL DEFAULT 'NULL',
  `status` int(11) NOT NULL,
  `actdate` date DEFAULT NULL,
  `expired` date DEFAULT NULL,
  `regdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `borrowcard`
--

INSERT INTO `borrowcard` (`id`, `cardno`, `status`, `actdate`, `expired`, `regdate`) VALUES
('duytt12', 'NULL', 0, NULL, NULL, '2018-05-14'),
('kientt97', 'NULL', 0, NULL, NULL, '2018-05-14'),
('kienvv2903', 'BCLIBRARY2', 1, '2018-05-13', '2019-11-21', '2018-05-07'),
('linhluv1', 'BCLIBRARY1', 1, '2018-05-07', '2019-05-24', '2018-05-05'),
('linhluv2', 'BCLIBRARY3', 0, '2018-05-09', '2019-06-21', '2018-05-05'),
('linhluv3', 'BCLIBRARY4', 1, '2018-05-13', '2019-07-25', '2018-05-05'),
('linhtt1', 'BCLIBRARY5', 1, '2018-05-12', '2019-04-24', '2018-05-12'),
('linhtt2', 'BCLIBRARY6', 0, '2018-05-13', '2019-02-28', '2018-05-12'),
('linhtt3', 'BCLIBRARY7', 1, '2018-05-15', '2019-01-17', '2018-05-13'),
('linhtt4', 'BCLIBRARY8', 1, '2018-05-15', '2019-06-19', '2018-05-15'),
('linhtt5', 'NULL', 0, NULL, NULL, '2018-05-15'),
('linhtt6', 'NULL', 0, NULL, NULL, '2018-05-15'),
('linhtt7', 'BCLIBRARY9', 1, '2018-05-15', '2019-02-07', '2018-05-15'),
('linhtt8', 'BCLIBRARY10', 0, '2018-05-15', '2018-09-21', '2018-05-15'),
('linhtt97', 'BCLIBRARY11', 1, '2018-05-15', '2019-02-21', '2018-05-15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` varchar(10) NOT NULL,
  `category` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `category`) VALUES
('AM', 'Tự Động Hóa'),
('EM', 'Kinh Tế'),
('ET', 'Điện Tử Viễn Thông'),
('HH', 'Hóa Học'),
('IT', 'Công Nghệ Thông Tin'),
('OL', 'Đại Cương'),
('PC', 'Vật Lý Kỹ  Thuật'),
('TT', 'Dệt May');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `librarian`
--

CREATE TABLE `librarian` (
  `id` varchar(45) NOT NULL,
  `password` text NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `librarian`
--

INSERT INTO `librarian` (`id`, `password`, `name`, `email`, `phone`) VALUES
('libbot1', '8fd66f2dae0b752b770440c4e083b44b', 'Librarian Bot 2', 'suportLKL@gmail.com', '0964988774'),
('tkxdpm11', 'f956e831dadf9199a066c8f5d4e33ac8', 'Bot Library', 'botlibrary@gmail.com', '0123456789');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loanbook`
--

CREATE TABLE `loanbook` (
  `id` int(11) NOT NULL,
  `cardno` varchar(16) NOT NULL,
  `idlibrarian` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `money` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loanbook`
--

INSERT INTO `loanbook` (`id`, `cardno`, `idlibrarian`, `name`, `startdate`, `enddate`, `money`) VALUES
(1, 'BCLIBRARY1', 'tkxdpm11', 'Thân Tài Linh', '2018-05-10', '2018-06-14', 15000),
(2, 'BCLIBRARY4', 'tkxdpm11', 'Thân Văn Dương', '2018-05-11', '2019-04-10', 23000),
(3, 'BCLIBRARY2', 'tkxdpm11', 'Vũ Văn Kiên', '2018-05-11', '2019-03-22', 27000),
(4, 'BCLIBRARY4', 'tkxdpm11', 'Thân Văn Dương', '2018-05-12', '2018-10-18', 45000),
(5, 'BCLIBRARY3', 'tkxdpm11', 'Thân Văn Duy', '2018-05-12', '2018-11-12', 20000),
(6, 'BCLIBRARY3', 'tkxdpm11', 'Thân Văn Duy', '2018-05-12', '2018-11-12', 12000),
(7, 'BCLIBRARY1', 'tkxdpm11', 'Thân Tài Linh', '2018-05-12', '2019-02-12', 10000),
(8, 'BCLIBRARY2', 'tkxdpm11', 'Vũ Văn Kiên', '2018-05-12', '2018-11-12', 15000),
(9, 'BCLIBRARY3', 'tkxdpm11', 'Thân Văn Duy', '2018-05-12', '2018-10-17', 15000),
(10, 'BCLIBRARY2', 'tkxdpm11', 'Vũ Văn Kiên', '2018-05-12', '2018-11-16', 5000),
(11, 'BCLIBRARY5', 'tkxdpm11', 'Nguyễn Văn Linh', '2018-05-12', '2019-01-21', 22000),
(12, 'BCLIBRARY5', 'tkxdpm11', 'Nguyễn Văn Linh', '2018-05-12', '2018-10-24', 12000),
(13, 'BCLIBRARY5', 'tkxdpm11', 'Nguyễn Văn Linh', '2018-05-12', '2018-11-21', 12000),
(14, 'BCLIBRARY2', 'tkxdpm11', 'Vũ Văn Kiên', '2018-05-13', '2018-08-22', 29000),
(15, 'BCLIBRARY7', 'tkxdpm11', 'Nguyễn Ngọc Linh', '2018-05-15', '2019-01-16', 25000),
(16, 'BCLIBRARY4', 'tkxdpm11', 'Thân Văn Dương', '2018-05-15', '2018-08-16', 30000),
(17, 'BCLIBRARY11', 'tkxdpm11', 'Trần Văn Linh', '2018-05-15', '2019-01-24', 30000),
(18, 'BCLIBRARY5', 'tkxdpm11', 'Nguyễn Văn Linh', '2018-05-15', '2018-10-19', 22000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loandetail`
--

CREATE TABLE `loandetail` (
  `id` int(11) NOT NULL,
  `idcopy` varchar(20) NOT NULL,
  `title` varchar(45) NOT NULL,
  `isreturn` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loandetail`
--

INSERT INTO `loandetail` (`id`, `idcopy`, `title`, `isreturn`) VALUES
(1, 'ET1BCP10', 'Điện tử tương tự số', 0),
(1, 'ET1BCP11', 'Điện tử tương tự số', 1),
(1, 'ET1BCP4', 'Điện tử tương tự số', 1),
(2, 'EM1BCP1', 'Kinh tế lượng', 1),
(2, 'EM1BCP2', 'Kinh tế lượng', 1),
(3, 'EM1BCP3', 'Kinh tế lượng', 0),
(3, 'EM1BCP5', 'Kinh tế lượng', 0),
(4, 'EM1BCP6', 'Kinh tế lượng', 1),
(4, 'EM1BCP7', 'Kinh tế lượng', 1),
(4, 'EM1BCP8', 'Kinh tế lượng', 1),
(5, 'IT1BCP1', 'Kỹ thuật máy tính', 1),
(5, 'IT1BCP2', 'Kỹ thuật máy tính', 0),
(6, 'OL2BCP1', 'Vật lý đại cương 2', 0),
(7, 'HH2BCP1', 'Hóa hữu cơ', 1),
(8, 'EM2BCP1', 'Chứng khoán tuyển tập', 1),
(8, 'EM2BCP3', 'Chứng khoán tuyển tập', 1),
(9, 'EM2BCP7', 'Chứng khoán tuyển tập', 1),
(9, 'EM2BCP8', 'Chứng khoán tuyển tập', 1),
(10, 'OL2BCP10', 'Vật lý đại cương 2', 1),
(11, 'HH2BCP2', 'Hóa hữu cơ', 1),
(11, 'HH2BCP3', 'Hóa hữu cơ', 1),
(12, 'ETBCP1', 'Máy điện', 1),
(13, 'OL2BCP2', 'Vật lý đại cương 2', 1),
(13, 'OL2BCP3', 'Vật lý đại cương 2', 1),
(14, 'ET1BCP11', 'Điện tử tương tự số', 1),
(14, 'ET1BCP4', 'Điện tử tương tự số', 1),
(15, 'ET1BCP5', 'Điện tử tương tự số', 1),
(15, 'OL2BCP10', 'Vật lý đại cương 2', 1),
(15, 'OL2BCP2', 'Vật lý đại cương 2', 1),
(16, 'ET1BCP11', 'Điện tử tương tự số', 1),
(16, 'ET1BCP4', 'Điện tử tương tự số', 1),
(16, 'ET1BCP6', 'Điện tử tương tự số', 0),
(17, 'EM2BCP1', 'Chứng khoán tuyển tập', 0),
(17, 'OL2BCP3', 'Vật lý đại cương 2', 0),
(17, 'OL2BCP4', 'Vật lý đại cương 2', 0),
(18, 'HH2BCP1', 'Hóa hữu cơ', 0),
(18, 'HH2BCP2', 'Hóa hữu cơ', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `publisher`
--

CREATE TABLE `publisher` (
  `id` varchar(10) NOT NULL,
  `pub` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `publisher`
--

INSERT INTO `publisher` (`id`, `pub`) VALUES
('BK', 'Đại học Bách Khoa'),
('DHQG', 'Đại học Quốc gia'),
('DN', 'Đồng Nai'),
('GD', 'Giáo dục'),
('KD', 'Kim Đồng'),
('SP', 'Đại học sư phạm');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `registebook`
--

CREATE TABLE `registebook` (
  `id` int(11) NOT NULL,
  `idcard` varchar(16) NOT NULL,
  `name` varchar(45) NOT NULL,
  `regdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `registebook`
--

INSERT INTO `registebook` (`id`, `idcard`, `name`, `regdate`) VALUES
(3, 'BCLIBRARY9', 'Vũ Văn Linh', '2018-05-15'),
(4, 'BCLIBRARY10', 'Nguyễn Ngọc Phương Linh', '2018-05-15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `registedetail`
--

CREATE TABLE `registedetail` (
  `id` int(11) NOT NULL,
  `idcopy` varchar(20) NOT NULL,
  `title` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `registedetail`
--

INSERT INTO `registedetail` (`id`, `idcopy`, `title`) VALUES
(3, 'EM1BCP1', 'Kinh tế lượng'),
(3, 'EM1BCP2', 'Kinh tế lượng'),
(3, 'IT1BCP10', 'Kỹ thuật máy tính'),
(3, 'IT1BCP11', 'Kỹ thuật máy tính'),
(4, 'IT1BCP1', 'Kỹ thuật máy tính'),
(4, 'IT1BCP12', 'Kỹ thuật máy tính'),
(4, 'IT1BCP13', 'Kỹ thuật máy tính');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `returndetail`
--

CREATE TABLE `returndetail` (
  `id` int(11) NOT NULL,
  `idCopy` varchar(20) NOT NULL,
  `title` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `returndetail`
--

INSERT INTO `returndetail` (`id`, `idCopy`, `title`) VALUES
(1, 'OL2BCP2', 'Vật lý đại cương 2'),
(1, 'OL2BCP3', 'Vật lý đại cương 2'),
(2, 'ETBCP1', 'Máy điện'),
(3, 'HH2BCP2', 'Hóa hữu cơ'),
(3, 'HH2BCP3', 'Hóa hữu cơ'),
(4, 'OL2BCP10', 'Vật lý đại cương 2'),
(5, 'EM2BCP8', 'Chứng khoán tuyển tập'),
(6, 'EM2BCP7', 'Chứng khoán tuyển tập'),
(7, 'EM2BCP3', 'Chứng khoán tuyển tập'),
(8, 'ET1BCP4', 'Điện tử tương tự số'),
(9, 'HH2BCP1', 'Hóa hữu cơ'),
(10, 'ET1BCP11', 'Điện tử tương tự số'),
(11, 'ET1BCP11', 'Điện tử tương tự số'),
(11, 'ET1BCP4', 'Điện tử tương tự số'),
(12, 'EM2BCP1', 'Chứng khoán tuyển tập'),
(13, 'EM1BCP6', 'Kinh tế lượng'),
(13, 'EM1BCP7', 'Kinh tế lượng'),
(13, 'EM1BCP8', 'Kinh tế lượng'),
(14, 'IT1BCP1', 'Kỹ thuật máy tính'),
(15, 'ET1BCP11', 'Điện tử tương tự số'),
(16, 'EM1BCP1', 'Kinh tế lượng'),
(16, 'EM1BCP2', 'Kinh tế lượng'),
(17, 'ET1BCP4', 'Điện tử tương tự số'),
(18, 'ET1BCP5', 'Điện tử tương tự số'),
(18, 'OL2BCP10', 'Vật lý đại cương 2'),
(18, 'OL2BCP2', 'Vật lý đại cương 2');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `returninfor`
--

CREATE TABLE `returninfor` (
  `id` int(11) NOT NULL,
  `idloan` int(11) NOT NULL,
  `cardno` varchar(16) NOT NULL,
  `idlib` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `returndate` date NOT NULL,
  `money` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `returninfor`
--

INSERT INTO `returninfor` (`id`, `idloan`, `cardno`, `idlib`, `name`, `returndate`, `money`) VALUES
(1, 13, 'BCLIBRARY5', 'tkxdpm11', 'Nguyễn Văn Linh', '2018-05-12', 10),
(2, 12, 'BCLIBRARY5', 'tkxdpm11', 'Nguyễn Văn Linh', '2018-05-12', 0),
(3, 11, 'BCLIBRARY5', 'tkxdpm11', 'Nguyễn Văn Linh', '2018-05-12', 10),
(4, 10, 'BCLIBRARY2', 'tkxdpm11', 'Vũ Văn Kiên', '2018-05-13', 0),
(5, 9, 'BCLIBRARY3', 'tkxdpm11', 'Thân Văn Duy', '2018-05-13', 0),
(6, 9, 'BCLIBRARY3', 'tkxdpm11', 'Thân Văn Duy', '2018-05-13', 0),
(7, 8, 'BCLIBRARY2', 'tkxdpm11', 'Vũ Văn Kiên', '2018-05-13', 0),
(8, 1, 'BCLIBRARY1', 'tkxdpm11', 'Thân Tài Linh', '2018-05-13', 0),
(9, 7, 'BCLIBRARY1', 'tkxdpm11', 'Thân Tài Linh', '2018-05-13', 0),
(10, 1, 'BCLIBRARY1', 'tkxdpm11', 'Thân Tài Linh', '2018-05-13', 0),
(11, 14, 'BCLIBRARY2', 'tkxdpm11', 'Vũ Văn Kiên', '2018-05-13', 10),
(12, 8, 'BCLIBRARY2', 'tkxdpm11', 'Vũ Văn Kiên', '2018-05-13', 0),
(13, 4, 'BCLIBRARY4', 'tkxdpm11', 'Thân Văn Dương', '2018-05-13', 0),
(14, 5, 'BCLIBRARY3', 'tkxdpm11', 'Thân Văn Duy', '2018-05-14', 0),
(15, 16, 'BCLIBRARY4', 'tkxdpm11', 'Thân Văn Dương', '2018-05-15', 0),
(16, 2, 'BCLIBRARY4', 'tkxdpm11', 'Thân Văn Dương', '2018-05-15', 10),
(17, 16, 'BCLIBRARY4', 'tkxdpm11', 'Thân Văn Dương', '2018-05-15', 0),
(18, 15, 'BCLIBRARY7', 'tkxdpm11', 'Nguyễn Ngọc Linh', '2018-05-15', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `sex` varchar(5) NOT NULL,
  `studentId` varchar(16) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `period` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `name`, `sex`, `studentId`, `email`, `phone`, `period`) VALUES
('duytt12', 'Bùi Văn Cường', 'Nam', '20159988', 'duongbui@gmail.com', '0964988900', 20171),
('kientt97', 'Vũ Văn Kiên', 'Nam', '20156678', 'kientt1997@gmail.com', '01698875544', 20171),
('kienvv2903', 'Vũ Văn Kiên', 'Nam', '20152238', 'vukien2903@gmail.com', '01636428930', 20172),
('librarian', 'Librarian Bot', 'Nữ', '123488796655', 'suport@student.hust.edu.vn', '01236666666', NULL),
('linhluv1', 'Thân Tài Linh', 'Nam', '20153433', 'linhcuong1212@gmail.com', '0964988900', 20172),
('linhluv1sss', 'Nguyễn Duy Linh', 'Nam', '20153348', 'linhcuong@gmail.com', '0988777889', 20152),
('linhluv2', 'Thân Văn Duy', 'Nam', '20152233', '20156322@student.hust.edu.vn', '09668887744', 20172),
('linhluv3', 'Thân Văn Dương', 'Nam', '20156678', 'linhtt97@gmail.com', '0965554474', 20172),
('linhtt1', 'Nguyễn Văn Linh', 'Nam', '20658890', 'linhtt79@gmail.com', '0966555996', 20171),
('linhtt2', 'Nguyễn Phương Linh', 'Nữ', '20163322', 'phuonglinh@gmail.com', '0968998990', 20172),
('linhtt3', 'Nguyễn Ngọc Linh', 'Nữ', '20154467', 'linhtt78@gmail.com', '0966552554', 20172),
('linhtt4', 'Thân Văn Linh', 'Nam', '20156778', 'linhtt98@gmail.com', '0964988922', 20172),
('linhtt5', 'Nguyễn Duy Linh', 'Nam', '20152234', 'linh1997tt@gmail.com', '0964988977', 20172),
('linhtt6', 'Thân Mạnh Linh', 'Nam', '20152266', 'linhtt67@gmail.com', '0964899800', 20172),
('linhtt7', 'Vũ Văn Linh', 'Nam', '20165578', 'linhtt@gmail.com', '0964988900', 20172),
('linhtt8', 'Nguyễn Ngọc Phương Linh', 'Nữ', '20152230', 'linhtt98@gmail.com', '0987666777', 20171),
('linhtt97', 'Trần Văn Linh', 'Nam', '20156688', 'linhtt1997@gmail.com', '0964900988', 20172);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_category` (`idcat`),
  ADD KEY `fk_pub` (`idpub`);

--
-- Chỉ mục cho bảng `bookcopy`
--
ALTER TABLE `bookcopy`
  ADD PRIMARY KEY (`idbook`,`idcopy`),
  ADD KEY `2_idx` (`idcopy`);

--
-- Chỉ mục cho bảng `borrowcard`
--
ALTER TABLE `borrowcard`
  ADD PRIMARY KEY (`id`,`cardno`),
  ADD KEY `fk_cardno_idx` (`cardno`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `librarian`
--
ALTER TABLE `librarian`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loanbook`
--
ALTER TABLE `loanbook`
  ADD PRIMARY KEY (`id`,`cardno`),
  ADD KEY `fk_cardno_idx` (`cardno`),
  ADD KEY `fk_idlib` (`idlibrarian`);

--
-- Chỉ mục cho bảng `loandetail`
--
ALTER TABLE `loandetail`
  ADD PRIMARY KEY (`id`,`idcopy`),
  ADD KEY `fk_idcopy` (`idcopy`);

--
-- Chỉ mục cho bảng `publisher`
--
ALTER TABLE `publisher`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `registebook`
--
ALTER TABLE `registebook`
  ADD PRIMARY KEY (`id`,`idcard`),
  ADD KEY `fk_cardnoregis` (`idcard`);

--
-- Chỉ mục cho bảng `registedetail`
--
ALTER TABLE `registedetail`
  ADD PRIMARY KEY (`id`,`idcopy`),
  ADD KEY `fk_idcopyreg` (`idcopy`);

--
-- Chỉ mục cho bảng `returndetail`
--
ALTER TABLE `returndetail`
  ADD PRIMARY KEY (`id`,`idCopy`),
  ADD KEY `fk_id_copy_return` (`idCopy`);

--
-- Chỉ mục cho bảng `returninfor`
--
ALTER TABLE `returninfor`
  ADD PRIMARY KEY (`id`,`idloan`,`cardno`),
  ADD KEY `fk_idloan` (`idloan`),
  ADD KEY `fk_cardnumber` (`cardno`),
  ADD KEY `fk_idLibs` (`idlib`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `2` FOREIGN KEY (`username`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`idcat`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pub` FOREIGN KEY (`idpub`) REFERENCES `publisher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `bookcopy`
--
ALTER TABLE `bookcopy`
  ADD CONSTRAINT `1` FOREIGN KEY (`idbook`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `borrowcard`
--
ALTER TABLE `borrowcard`
  ADD CONSTRAINT `fk_iduser` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `loanbook`
--
ALTER TABLE `loanbook`
  ADD CONSTRAINT `fk_bookCopies` FOREIGN KEY (`cardno`) REFERENCES `borrowcard` (`cardno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idlib` FOREIGN KEY (`idlibrarian`) REFERENCES `librarian` (`id`);

--
-- Các ràng buộc cho bảng `loandetail`
--
ALTER TABLE `loandetail`
  ADD CONSTRAINT `fk_loanid` FOREIGN KEY (`id`) REFERENCES `loanbook` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `registebook`
--
ALTER TABLE `registebook`
  ADD CONSTRAINT `fk_cardnoregis` FOREIGN KEY (`idcard`) REFERENCES `borrowcard` (`cardno`);

--
-- Các ràng buộc cho bảng `registedetail`
--
ALTER TABLE `registedetail`
  ADD CONSTRAINT `fk_idreg` FOREIGN KEY (`id`) REFERENCES `registebook` (`id`);

--
-- Các ràng buộc cho bảng `returndetail`
--
ALTER TABLE `returndetail`
  ADD CONSTRAINT `fk_id_return` FOREIGN KEY (`id`) REFERENCES `returninfor` (`id`);

--
-- Các ràng buộc cho bảng `returninfor`
--
ALTER TABLE `returninfor`
  ADD CONSTRAINT `fk_idLibs` FOREIGN KEY (`idlib`) REFERENCES `librarian` (`id`),
  ADD CONSTRAINT `fk_idcarduser` FOREIGN KEY (`cardno`) REFERENCES `loanbook` (`cardno`),
  ADD CONSTRAINT `fk_idloan` FOREIGN KEY (`idloan`) REFERENCES `loanbook` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
