-- noinspection SqlNoDataSourceInspectionForFile

--
-- Table structure for table `blacklisted_host_lead`
--

CREATE TABLE `blacklisted_host_lead` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `remote_host` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `blacklisted_host` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY,
  `remote_host` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE lead
ADD COLUMN remote_host varchar(255) DEFAULT NULL;

CREATE INDEX idx__lead__remote_host__create_date
ON lead (remote_host, create_date);

CREATE INDEX idx__blacklisted_host_lead__remote_host__create_date
ON blacklisted_host_lead (remote_host, create_date);

CREATE INDEX idx__blacklisted_host__remote_host
ON blacklisted_host (remote_host);

