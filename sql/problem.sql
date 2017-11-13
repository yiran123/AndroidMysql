
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProblemNo` int(11) DEFAULT NULL,
  `Operand1` int(11) DEFAULT NULL,
  `Operand2` int(11) DEFAULT NULL,
  `Operation` char(255) DEFAULT NULL,
  `AnsweredCorrectly` int(11) DEFAULT NULL,
  `TestID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `TestID` (`TestID`),
  CONSTRAINT `TestID` FOREIGN KEY (`TestID`) REFERENCES `test` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
