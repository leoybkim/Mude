

<img src='http://i.imgur.com/ZHK9sdl.gif' title='Video Walkthrough' width='' alt='Video Walkthrough'/> 

## Inspiration
Have you ever wished you can hear your favourite music at the *same* volume no matter how far away you are?
Well we all know you have been wanting to solve this problem!

## What it does
Mude levels out the speaker volume no matter where you stand in the room. The further away you are from the speaker, the louder it plays, and conversely, the closer you stand, the softer the sound. Mude is a smart music playing app that allows users to continue their daily routines with consistent music without having to touch a button.

## How we built it
We utilized the RSSI values to convert Bluetooth signal strength to distance in meters between devices. This was done through the Estimote's Android SDK library and Bluetooth LE. We also used the Spotify web API to play the music matching the user's mood.

## Challenges we ran into
The most difficult challenge we have faced while developing was implementing third party APIs. Especially we had trouble integrating Spotify's Android SDK since the authentication documentations were outdated, unsupported and beta versions were unstable. Another problem we ran into was memory leaks while using the Estimote proximity beacons. We found out that the services launched while running the app  didn't completely shut off after the app was closed. 

## Accomplishments that we're proud of
Longest hours stayed awake: 33 hours (Barick)  
Most cups of coffees consumed: 9 cups (Leo)  
Finishing a bowl of spicy noodles (Tara)  
Youngest participant grade 10 (Faizaan)  

## What we learned
~~Spotify API sucks~~
Discovered the existence of Estimotes and the beauty of energy efficient Bluetooth beacons and how to use them.

## What's next for Mude
Our team's goal is to implement a machine learning algorithm to generate a music playlist that best represents the user's mood while also suiting their taste in music. We would like to learn more about integrating facial recognition to better detect the user's mood.
