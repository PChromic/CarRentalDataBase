 SET sql_mode='ONLY_FULL_GROUP_BY,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -- -------------------------------------------------------------------------------
-- Section: DROP DATABASE lub DROP Tables
-- -- -------------------------------------------------------------------------------
-- Tutaj usuwamy całą bazę przed jej zadeklarowaniem. To pozwoli Wam łatwo resetować
-- bazę danych w trakcie testów.
-- -- -------------------------------------------------------------------------------

 DROP DATABASE IF EXISTS jstk_jpa;
 CREATE database jstk_jpa;
 
-- -- -------------------------------------------------------------------------------
-- Section: USE
-- Zamien 'jsk_v7_jan_kowalski' na poprawna nazwe Twojej bazy danych
-- Format nazwy DB: jsk_v7_[imie]_[nazwisko]
-- -- -------------------------------------------------------------------------------

 USE jstk_jpa;
 

CREATE USER 'jstk' identified by 'jstk';
GRANT ALL PRIVILEGES ON jstk_jpa.* TO 'jstk';