CREATE DATABASE `sugestao_curso`;

CREATE TABLE `sugestao_curso`.`tb_curso` (
  `nm_curso` VARCHAR(50) NOT NULL ,
  `ds_descricao` VARCHAR(150) NOT NULL ,
  `ds_email` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`nm_curso`) 
);


CREATE  TABLE `sugestao_curso`.`tb_voto` (
  `nm_curso` VARCHAR(50) NOT NULL ,
  `ds_email` VARCHAR(255) NOT NULL ,
  
  PRIMARY KEY (`nm_curso`, `ds_email`) ,
  
  INDEX `fk_nm_curso` (`nm_curso` ASC) ,
  
  CONSTRAINT `fk_nm_curso` 
    FOREIGN KEY (`nm_curso`)
    REFERENCES `sugestao_curso`.`tb_curso` (`nm_curso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    
);
