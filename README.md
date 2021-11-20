
# CrystalHack
![](https://img.shields.io/github/downloads/JohanDevv/CrystalHack/total?style=flat-square)
![](https://img.shields.io/tokei/lines/github/JohanDevv/CrystalHack?style=flat-square)
![](https://img.shields.io/github/languages/code-size/JohanDevv/CrystalHack?style=flat-square)
![](https://img.shields.io/github/last-commit/JohanDevv/CrystalHack?style=flat-square)
![](https://img.shields.io/badge/daily%20commit-yes-blue?style=flat-square)
![](https://img.shields.io/discord/903339460513636404?style=flat-square)

BleahHack based Utility mod for 1.17.
Approved by Obama

> Website: https://github.com/JohanDevv/CrystalHack  
> Discord: https://dsc.gg/CrystalHack

## Showcase
<details>
 <summary>Images</summary>

 ![](https://res.bleachhack.org/images/ClickguiShowcase.jpg)

 ![](https://res.bleachhack.org/images/RenderShowcase.jpg)

</details>

## Installation
### For normal people

Follow the Instructions on the [download page](https://bleachhack.org/downloads.html).

### For (200 IQ) developers

Download the branch with the version you want to work on.  
Start A Command Prompt/Terminal in the main folder.  
Generate the needed files for your preferred IDE.  

***Eclipse***

  On Windows:
  > gradlew genSources eclipse
  
  On Linux:
  > chmod +x ./gradlew  
  >./gradlew genSources eclipse

  Start a new workspace in eclipse.
  Click File > Import... > Gradle > Gradle Project.
  Select the Main folder.
  
***IntelliJ***

  On Windows:
  > gradlew genIdeaWorkspace
  
  On Linux:
  > chmod +x ./gradlew  
  >./gradlew genIdeaWorkspace

  In idea click File > Open.
  Select build.gradle in the main folder.
  Select Open as Project.

***Other IDE's***

  Use [this link](https://fabricmc.net/wiki/tutorial:setup) for more information.
  It should be pretty similar to the eclipse and idea setup.
  
###### *To get the source code of Pre-1.17 versions, use [this](https://github.com/BleachDrinker420/BleachHack/tree/de55562e94) commit and select the folder of the version you want.*

## Recommended Mods

Here are some nice to have mods that are compatible with BleachHack, none of these require Fabric API.

### [Multiconnect](https://github.com/Earthcomputer/multiconnect)
Multiconnect allows you to connect to any 1.8-1.17 server from a 1.17 client.

### [Baritone](https://github.com/cabaletta/baritone)
Baritone allows you to automate tasks such as walking, mining or building.

## License

If you are distributing a custom version of BleachHack or a mod with ported features of BleachHack, you are **required** to disclose the source code, state changes, use a compatible license, and follow the [license terms](https://github.com/BleachDrinker420/BleachHack/blob/master/LICENSE).
