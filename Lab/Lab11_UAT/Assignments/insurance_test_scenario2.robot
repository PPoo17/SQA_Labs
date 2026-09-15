*** Settings ***
Documentation     UAT-Lab11-002: การขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ไม่สำเร็จ
Library           SeleniumLibrary

Test Setup        เปิดเบราว์เซอร์เข้าสู่ระบบ
Test Teardown     Close Browser

*** Variables ***
${URL}                  http://sampleapp.tricentis.com/
${BROWSER}              chrome
${TIMEOUT}              10s

# Step 1 & 2 Locators: Enter Vehicle Data
${MENU_AUTOMOBILE}      id=nav_automobile
${TAB_VEHICLE}          id=entervehicledata
${MAKE}                 id=make
${BTN_NEXT_INSURANT}    id=nextenterinsurantdata

# Step 3 Locators: Enter Insurant Data
${TAB_INSURANT}         id=enterinsurantdata
${BTN_NEXT_PRODUCT}     id=nextenterproductdata

# Step 4 Locators: Enter Product Data
${TAB_PRODUCT}          id=enterproductdata
${BTN_NEXT_PRICE}       id=nextselectpriceoption

# Verification Element (Package Selection)
${SELECT_SILVER}        id=selectsilver

*** Test Cases ***
UAT-Lab11-002 การขอใบเสนอราคาผลิตภัณฑ์สำหรับรถยนต์ไม่สำเร็จ
    [Documentation]    กรอกข้อมูลไม่สมบูรณ์/ไม่ถูกต้องเพื่อทดสอบการแจ้งเตือนและไม่สามารถไปหน้าเลือก Package ได้

  
    # Step 1: Open Insurance Main Page
    Wait Until Page Contains Element    ${MENU_AUTOMOBILE}    timeout=${TIMEOUT}
    Wait Until Element Is Visible    ${MENU_AUTOMOBILE}       timeout=${TIMEOUT}
    Click Element                    ${MENU_AUTOMOBILE}

    # Step 2: Enter Invalid Vehicle Data for Automobile
    # 1-8. เว้นว่างข้อมูลรถยนต์ (ไม่กรอกข้อมูล) ตาม Scenario
    Wait Until Element Is Visible    ${MAKE}                  timeout=${TIMEOUT}

    # 9. กดปุ่ม "Next"
    Wait Until Element Is Visible    ${BTN_NEXT_INSURANT}     timeout=${TIMEOUT}
    Wait Until Element Is Enabled    ${BTN_NEXT_INSURANT}     timeout=${TIMEOUT}
    Click Element                    ${BTN_NEXT_INSURANT}

    # Verification Step 2: ตรวจสอบสัญลักษณ์แจ้งเตือน Error บน Tab Enter Vehicle Data
    Wait Until Page Contains Element    xpath=//a[@id='entervehicledata']//span[contains(@class,'counter')]    timeout=${TIMEOUT}

    # Step 3: Enter Incomplete Insurance Data
    # 1-10. พยายามทดสอบกดปุ่ม Next ของหน้า Insurant Data
    Run Keyword And Ignore Error    Click Element    ${TAB_INSURANT}
    Run Keyword And Ignore Error    Click Element    ${BTN_NEXT_PRODUCT}

    # Verification Step 3: ตรวจสอบสัญลักษณ์แจ้งเตือน Error ของ Tab Enter Insurant Data
    Wait Until Page Contains Element    xpath=//a[@id='enterinsurantdata']//span[contains(@class,'counter')]    timeout=${TIMEOUT}

    # Step 4: Enter Incomplete Product Data
    # 1-6. พยายามทดสอบกดปุ่ม Next ของหน้า Product Data
    Run Keyword And Ignore Error    Click Element    ${TAB_PRODUCT}
    Run Keyword And Ignore Error    Click Element    ${BTN_NEXT_PRICE}

    # Verification Step 4: ตรวจสอบสัญลักษณ์แจ้งเตือน Error ของ Tab Product Data
    Wait Until Page Contains Element    xpath=//a[@id='enterproductdata']//span[contains(@class,'counter')]    timeout=${TIMEOUT}

    # Final Verification: ยืนยันว่าไม่สามารถเข้าถึงตัวเลือก Package (Silver) ได้
    Element Should Not Be Visible       ${SELECT_SILVER}

*** Keywords ***
เปิดเบราว์เซอร์เข้าสู่ระบบ
    Open Browser    ${URL}    ${BROWSER}
    Maximize Browser Window