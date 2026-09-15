*** Settings ***
Documentation     Lab 11 - UAT-Lab11-001 Request Vehicle Insurance Quote Success
Library           SeleniumLibrary

*** Variables ***
${CHROME_BROWSER_PATH}    C:${/}Users${/}Kao${/}Downloads${/}ChromeForTesting${/}chrome.exe
${CHROME_DRIVER_PATH}     C:${/}Users${/}Kao${/}Downloads${/}ChromeForTesting${/}chromedriver.exe
${URL}                    http://sampleapp.tricentis.com/

*** Test Cases ***
UAT-Lab11-001 Request Vehicle Insurance Quote Success
    [Documentation]    การขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ได้สำเร็จ
    Open Insurance Main Page
    Enter Vehicle Data for Automobile
    Enter Insurance Data
    Enter Product Data
    Select Price Option
    Send Quote
    [Teardown]    Close Browser

*** Keywords ***
Open Insurance Main Page
    ${chrome_options}=    Evaluate    sys.modules['selenium.webdriver'].ChromeOptions()    sys
    ${chrome_options.binary_location}=    Set Variable    ${CHROME_BROWSER_PATH}
    ${service}=    Evaluate    sys.modules["selenium.webdriver.chrome.service"].Service(executable_path=r"${CHROME_DRIVER_PATH}")
    Create Webdriver    Chrome    options=${chrome_options}    service=${service}
    Go To    ${URL}
    Maximize Browser Window

Enter Vehicle Data for Automobile
    Wait Until Page Contains Element    id:nav_automobile    10s
    Click Element    id:nav_automobile
    Wait Until Page Contains Element    id:make    10s
    Select From List By Value    id:make    BMW
    Input Text    id:engineperformance    110
    Input Text    id:dateofmanufacture    09/09/2020
    Select From List By Value    id:numberofseats    5
    Select From List By Value    id:fuel    Electric Power
    Input Text    id:listprice    30000
    Input Text    id:licenseplatenumber    CKK1234
    Input Text    id:annualmileage    10000
    Execute JavaScript    document.getElementById('nextenterinsurantdata').click();

Enter Insurance Data
    Wait Until Page Contains Element    id:firstname    10s
    Input Text    id:firstname    Wichai
    Input Text    id:lastname    Sandee
    Input Text    id:birthdate    01/31/1990
    Execute JavaScript    document.getElementById('gendermale').click();
    Input Text    id:streetaddress    KKU
    Select From List By Value    id:country    Thailand
    Input Text    id:zipcode    40002
    Input Text    id:city    Khon Kaen
    Select From List By Value    id:occupation    Employee
    Execute JavaScript    document.getElementById('other').click();
    Execute JavaScript    document.getElementById('nextenterproductdata').click();

Enter Product Data
    Wait Until Page Contains Element    id:startdate    10s
    # กำหนดวันที่ในอนาคตมากกว่า 1 เดือน (12/01/2026) เพื่อให้ผ่าน validation
    Input Text    id:startdate    12/01/2026
    Select From List By Value    id:insurancesum    7000000
    Select From List By Value    id:meritrating    Bonus 1
    Select From List By Value    id:damageinsurance    No Coverage
    Execute JavaScript    document.getElementById('EuroProtection').click();
    Select From List By Value    id:courtesycar    Yes
    Sleep    1s
    Execute JavaScript    document.getElementById('nextselectpriceoption').click();

Select Price Option
    Wait Until Page Contains Element    id:selectsilver    10s
    Execute JavaScript    document.getElementById('selectsilver').click();
    Sleep    1s
    Execute JavaScript    document.getElementById('nextsendquote').click();

Send Quote
    Wait Until Page Contains Element    id:email    10s
    Input Text    id:email    wichai.sandee@gmail.com
    Input Text    id:phone    0049201123456
    Input Text    id:username    wichai.sandee
    Input Text    id:password    SecretPassword123!
    Input Text    id:confirmpassword    SecretPassword123!
    Input Text    id:Comments    Please contact via email only
    Execute JavaScript    document.getElementById('sendemail').click();
    Wait Until Page Contains    Sending e-mail success!    30s