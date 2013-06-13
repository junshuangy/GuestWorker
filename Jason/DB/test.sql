CREATE DATABASE  IF NOT EXISTS `TEST`;
USE `TEST`;

DROP TABLE IF EXISTS `TB_COMPOUND`;

CREATE TABLE `TB_COMPOUND` (
  `COMPOUND_CODE` INT(11) NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  `CAS` VARCHAR(255),
  `SMILES` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`COMPOUND_CODE`)
);

INSERT INTO `TB_COMPOUND` VALUES (1,'estradiol','50-28-2; 73459-61-7;','C[C@]12CC[C@H]3[C@@H](CCc4cc(O)ccc34)[C@@H]1CC[C@@H]2O'),(2,'tamoxifen','10540-29-1;','CC\\\\C(=C(/c1ccccc1)\\\\c2ccc(OCCN(C)C)cc2)\\\\c3ccccc3'),(3,'genistein','446-72-0;','Oc1ccc(cc1)C2=COc3cc(O)cc(O)c3C2=O'),(4,'2-(4-hydroxyphenyl)-3-{4-[2-(piperidin-1-yl)ethoxy]benzoyl}-1-benzothiophen-6-ol','84449-90-1;','Oc1ccc(cc1)c2sc3cc(O)ccc3c2C(=O)c4ccc(OCCN5CCCCC5)cc4'),(5,'4-hydroxytamoxifen','72732-26-4; 68392-35-8; 76276-99-8; 65213-48-1;','CC\\\\C(=C(/c1ccc(O)cc1)\\\\c2ccc(OCCN(C)C)cc2)\\\\c3ccccc3'),(6,'diethylstilbestrol','56-53-1; 8026-45-7; 8028-09-9; 8030-34-0; 8049-42-1; 8053-00-7; 6898-97-1; 22610-99-7;','CC\\\\C(=C(\\\\CC)/c1ccc(O)cc1)\\\\c2ccc(O)cc2'),(7,'estrone','53-16-7; 37242-41-4;','C[C@]12CC[C@H]3[C@@H](CCc4cc(O)ccc34)[C@@H]1CCC2=O'),(8,'5,14-dihydroxy-8,17-dioxatetracyclo[8.7.0.0^{2,7}.0^{11,16}]heptadeca-1(10),2,4,6,11(16),12,14-heptaen-9-one','479-13-0;','Oc1ccc2c(OC(=O)c3c2oc4cc(O)ccc34)c1'),(9,'ethinyl estradiol','77538-56-8; 57-63-6;','C[C@]12CC[C@H]3[C@@H](CCc4cc(O)ccc34)[C@@H]1CC[C@@]2(O)C#C'),(10,'daidzein','486-66-8;','Oc1ccc(cc1)C2=COc3cc(O)ccc3C2=O'),(11,'4-nonylphenol','104-40-5; 29832-11-9; 68081-86-7;','CCCCCCCCCc1ccc(O)cc1'),(12,'(1S,9R,10R,11S,14S,15S)-15-methyl-9-[9-(4,4,5,5,5-pentafluoropentanesulfinyl)nonyl]tetracyclo[8.7.0.0^{2,7}.0^{11,15}]heptadeca-2(7),3,5-triene-5,14-diol','','C[C@]12CC[C@H]3[C@@H]([C@H](CCCCCCCCC[S+]([O-])CCCC(F)(F)C(F)(F)F)Cc4cc(O)ccc34)[C@@H]1CC[C@@H]2O'),(13,'estriol','50-27-1;','C[C@]12CC[C@H]3[C@@H](CCc4cc(O)ccc34)[C@@H]1C[C@@H](O)[C@@H]2O'),(14,'dihydrodiethylstilbestrol','','CCC(C(CC)c1ccc(O)cc1)c2ccc(O)cc2'),(15,'(2S,3R)-3-(4-hydroxyphenyl)-2-{4-[2-(piperidin-1-yl)ethoxy]phenyl}-2,3-dihydro-1,4-benzoxathiin-6-ol','','Oc1ccc(cc1)[C@H]2Sc3cc(O)ccc3O[C@H]2c4ccc(OCCN5CCCCC5)cc4'),(16,'progesterone','257630-50-5; 753497-20-0; 57-83-0; 8023-13-0; 8012-32-6;','CC(=O)[C@H]1CC[C@H]2[C@@H]3CCC4=CC(=O)CC[C@]4(C)[C@H]3CC[C@]12C'),(17,'diano','27100-33-0; 80-05-7; 137885-53-1; 27360-89-0; 28106-82-3; 37808-08-5;','CC(C)(c1ccc(O)cc1)c2ccc(O)cc2'),(18,'1-chloro-2-[2,2,2-trichloro-1-(4-chlorophenyl)ethyl]benzene','','Clc1ccc(cc1)C(c2ccccc2Cl)C(Cl)(Cl)Cl'),(19,'(-)-zearalenone','17924-92-4;','C[C@H]1CCCC(=O)CCC\\\\C=C\\\\c2cc(O)cc(O)c2C(=O)O1');

