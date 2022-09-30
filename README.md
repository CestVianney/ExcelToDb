# ExcelToDb
Back &amp; front for an .xlsx reader

-- BACK -- 
Java app using Spring boot, Apache POI & Javax swing librairies + H2 onboardable database
This application is made for a very specific usage only
By importing an .xlsx file, the app converts from values depending on few rules to a table, which will be read by an API by the front end

-- FRONT --
Amateurish front where JS calls the local API for retrieving values read by backend
Background image are randomly retrieved by the Lorem Ipsum of APIs (picsum.photos), depending on the state of the app (API check if it's live or not)


Application designed for a very specific use, no upgrades are even planned -which explains why there's no unitary tests-
