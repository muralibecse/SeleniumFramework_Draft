Echo off
Title Server HUB on port 4444

Echo Setting Project Location
set projectLocatoion = C:\Users\PC\git\SeleniumFramework_Draft_2\Jars
pushd %projectLocation%

Echo Loading HUB on default port 4444
java -Dwebdriver.chrome.driver=C:\Users\PC\git\SeleniumFramework_Draft_2\Drivers\chromedriver.exe -jar selenium-server-standalone-3.141.59.jar
pause