# Mellow Dex

A Marshmalow Pokédex.

<p align="center">
![alt text](https://github.com/Zayd-Waves/mellow-dex/blob/master/screenshots/charizard.gif "Charizard")
</p>

## About
In anticipation for the upcoming Pokémon [Sun and Moon](http://www.serebii.net/sunmoon/) games, ol' Professor Zayd here decided it was time to make a fancy new Pokédex.

## The Data
I want this application to store all the Pokémon-related information for the **current main series games only**. So as of August 2016, that means all 726 Pokémon, every ability, every move. This will change as time goes on.

As tempting as it is to store all the data on a web server, my original goal was to provide a pokedex with all the data you'll need bundled up with it. There is a *ton* of information that needs to be stored though, so efficiency is key. But since I can totally see myself regretting this later on down the line, a forked, lighter-weight version of the Pokédex is likely.

## Download
Right now if you want to download the app to your phone, yo'll have to download the source code and build an .apk yourself. For the second option you'll need to:
* Download Android Studio
* Import the code you downloaded as an Android Studio Project
* Build -> Generate Signed APK.

The next few steps will ask you to sign the app using a keystore. These fields don't really matter too much unless you plan on publishing an app to the play store. You can fill in these fields with whatever for now (If you'd rather not sign the app, then go to Build -> Build APK instead). Take note of where you saved the .apk file and download it to your phone.

## Credits
First off, a lot of credit goes to Awestruck Studios' [Dexter app.](https://play.google.com/store/apps/details?id=com.awestruckstudios.pkmn&hl=en) Their Android Pokedex was what inspired me to make my own. I really love Dexter and I use it all the time so please check it out on the Google Play Store!

The database I implemented is heavily based off of [Veekun's Pokedex Database](https://github.com/veekun/pokedex), another awesome person who works really hard on maintaining an up-to-date and extensive source of data.

## Contributing
Feel free to contribute any time you like! If you discover a bug or have an idea for a cool new feature you'd like to add, go ahead and create an issue or do a pull request. I'm also open to any and all suggestions.

## Published Version
I currently don't have any plans to publish this on the app store because of the copyrighted Pokémon data. If in the future I find a way to release a version that complies with Nintendo's trademarks, then sure! But until then you'll just have to load the apk manually.

## Copyright and License
This software comes bundled with data from the Pokémon series of video games. This is all the intellectual property of Nintendo, Creatures, inc., and GAME FREAK, inc. and is protected by various copyrights and trademarks.

In addition to the below license, if you use any part of Mellow Dex, I would appreciate a shout-out. :)

    Copyright 2016 Zayd Bille

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
