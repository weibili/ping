CREATE DATABASE weather;

USE weather;

CREATE TABLE `user` (
  `id` varchar(11) NOT NULL,
  `password` varchar(200) NOT NULL,
  `lastlogints` datetime,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `manager` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `tel` varchar(20) NOT null,
  `useFlag` varchar(1) NOT null,
  `sendType` varchar(1) NOT null,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `host` (
  `hostIp` varchar(15) NOT NULL,
  `hostName` varchar(14),
  `hostStatus` varchar(1),
  `pingFlag` varchar(1),
  `lastDownId` varchar(64),
  PRIMARY KEY  (`hostIp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `pingrecord` (
	`id` varchar(64) NOT NULL,
  `hostIp` varchar(15) NOT NULL,
  `hostName` varchar(14),
  `firstPingTs` datetime,
  `lastPingTs` datetime,
  `downTime` DOUBLE(12,2),
   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


create index IDX_PING_RECORD_1 ON pingrecord(firstPingTs);
create index IDX_PING_RECORD_2 ON pingrecord(lastPingTs);

commit;