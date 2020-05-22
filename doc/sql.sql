CREATE TABLE `game_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `game_id` bigint(20) NOT NULL COMMENT '游戏ID',
  `url` varchar(512) COLLATE utf8_unicode_ci NOT NULL COMMENT '图片地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `game_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gameId` bigint(20) NOT NULL COMMENT '18183游戏ID',
  `title` varchar(512) COLLATE utf8_unicode_ci NOT NULL COMMENT '游戏标题',
  `icon` varchar(512) COLLATE utf8_unicode_ci NOT NULL COMMENT '游戏图标',
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '游戏类型',
  `type_id` int(2) NOT NULL COMMENT '类型ID',
  `down_num` int(8) NOT NULL DEFAULT '0' COMMENT '下载量',
  `like_num` int(8) NOT NULL DEFAULT '0' COMMENT '点赞量',
  `view_num` int(8) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `score` int(8) NOT NULL DEFAULT '0' COMMENT '评分',
  `star_level` int(2) NOT NULL DEFAULT '0' COMMENT '评星等级',
  `android_file_size` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '安卓app大小',
  `android_down_url` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '安卓下载地址',
  `ios_file_size` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'iosapp大小',
  `ios_down_url` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'ios app下载地址',
  `content` text COLLATE utf8_unicode_ci COMMENT '游戏描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `game_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `short_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `news_id` bigint(20) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `type_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pub_time` datetime NOT NULL,
  `content` text COLLATE utf8_unicode_ci,
  `description` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lit_pic` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `source` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `source_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `delete_flag` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;