[strom]:https://www.github.com/w3-engineers/android-framework
[travis]:https://travis-ci.com/w3-engineers/
[coverall]:https://coveralls.io/github/w3-engineers/telemesh
[Apache License 2.0]:https://choosealicense.com/licenses/apache-2.0/


# Telemesh

[![Contributor Covenant](https://img.shields.io/badge/Contributor%20Covenant-v1.4%20adopted-ff69b4.svg)](CODE-OF-CONDUCT.md)
[![Build Status](https://travis-ci.com/w3-engineers/telemesh.svg?branch=master)](https://travis-ci.com/w3-engineers/telemesh)
[![Coverage Status](https://coveralls.io/repos/github/w3-engineers/telemesh/badge.svg?branch=master)](https://coveralls.io/github/w3-engineers/telemesh?branch=master)
[![Lint tool: TeleMesh](https://img.shields.io/badge/Lint_tool-telemesh-2e99e9.svg?style=flat)](https://w3-engineers.github.io/telemesh/lint_reports/lint-report.html)

A mesh network based off-grid messaging application supported by blockchain technology.

## Project Overview
### Background
Globally, 68.5 million people are forcibly displaced at the time of writing this readme, and over 25.4 million are refugees. In Bangladesh, there are over 650,000 Rohingya refugees who have fled violence, mass killings and sexual abuse from neighboring Myanmar.2 Of those, nearly 60% are children, many of whom are orphaned Distributing information about humanitarian services to large numbers of refugees poses significant challenges for NGOs like UNICEF. While 40% of rural refugee households have smartphones, many are unconnected due to a lack of or poor telecommunications infrastructure or unaffordable cellular costs. The UNHCR believes that connecting refugees would ultimately transform humanitarian operations.

### Project's Goal
We intend to make use of mesh network. It allows for multi-hop, peer-to-peer connectivity directly between smartphones, instead of relying on internet and cell networks. Blockchain is used in the network to uniquely identify each node (smartphone) providing a trust layer to users without centralized signup. It also provides the infrastructure for users to connect multiple separate meshes by sharing an internet connection in exchange for ERC20 tokens. This offers an entirely new and unique method of information distribution not possible with existing technology.

For UNICEF, we plan to develop an open source messaging app to be tested in refugee camps, specifically, Cox’s Bazar, Bangladesh. A broadcast channel would allow UNICEF to push vital information to smartphone users about services like vaccination clinics, maternity clinics and schools. The app would also allow refugees to message one another even if they do not have a SIM card or cellular data.


<kbd><img src="https://github.com/w3-engineers/telemesh/blob/feature/codeofconduct/mesh.png" align="center"></kbd>

## Feature list
* One to one messaging
* Message broadcast
* App sharing
* forthcoming


## Project's Structure

```
    .
    |-- app
    |-- src
    |-- main
        |-- com.w3engineers.unicef
            |-- telemesh
                |-- data #local database, file, shared preferences etc.
                |-- ui #ui components
            |-- util
                |-- helper #Generic tasks like TimeUtil, NetworkUtil etc.
                |-- lib #third party library, component etc.
            |-- Application.java #Android Application class    
    |-- viper #W3Engineers wrapper module
    |-- build.gradle
    |-- settings.gradle
    |-- gradle.properties
```

* **Alias**
N/A

* **Commands**
N/A

## Prerequisites
* Mesh networking technology. 
* Android device with Wifi, WifiDirect, Bluetooth or Bluetooth Low Energy (BLE) support.

## Project Dependencies
* **[Strom][strom]**: It is just a wrapper on native android to reduce some repeated works. This has been used as a dependency into this project.

* **Viper**: This is also a wrapper of a mesh library and has been used as a dependency.

## Development environments
We are using the below environment for android mobile app development:
* Minimum API: 19 (KitKat - 4.4.x)
* Java: 1.8.0_121
* Android Studio: 3.3 Stable
* Machine Used: Linux/Ubuntu, MacOS 10.14: Mojave (Liberty)

## How to get started
**Step 1: Clone repository:**
Navigate to directory where you want to keep source code. Open command prompt. Execute below command:
> git clone https://github.com/w3-engineers/telemesh.git


**Step 2: Prepare gradle.properties file:**
You need to request for mesh technology support to the Telemesh team to build the project successfully.

**Step 3: Sync and build:**
If everything is ok then sync and build should work as it should be. If not please recheck step 1 and 2. 

**Step 4: Test on device:**
[TBD]

## Test Coverage
* This repo is configured with [Travis][travis] and [coverall][coverall]. Every merge with *master* produced a test coverage report. Latest coverage report is available [here](https://coveralls.io/github/w3-engineers/telemesh?branch=master). [This](#Telemesh) badge here shows coverage status.

* To generate report locally you should go to project's root directory, then execute below command:
    > gradlew coveralls

**NOTE:** You must have a connected device or emulator as it runs instrumentation tests. You will find the coverage report at *telemesh/app/build/reports/coverage*

## User Interface (UI)
<kbd><img src="http://gdurl.com/eghW" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/Tjfb" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/mHBU0" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/vxRe" width="175" height="300"></kbd> 

<kbd><img src="http://gdurl.com/Un2g" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/wmHS" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/G9N1" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/0vRW" width="175" height="300"></kbd> 

<kbd><img src="http://gdurl.com/IbR1" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/fKqZ" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/gIwO" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/lCNV" width="175" height="300"></kbd>

<kbd><img src="http://gdurl.com/B3v8" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/eQcm" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/7C7Z" width="175" height="300"></kbd> <kbd><img src="http://gdurl.com/AbPW" width="175" height="300"></kbd>



## License
**[Apache License 2.0]**


