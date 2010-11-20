Jump To Usage
==============
Once upon a time (pre 7.x), the all awesome IntelliJ IDEA had but one glaring flaw.
To navigate to an usage of a symbol, you'd have to hit Alt + F7 on the symbol, click Find on the dialog that'd pop-out (assuming the defaults were okay), and in the Find view, expand the tree to find the usage you'd like to navigate to and Double-click/hit Enter on the usage.
See how tedious that is? At least 3-4 clicks/pauses just to navigate to a usage! Now that is entirely unacceptable in an IDE as awesome as IntelliJ.
Of course, doing the same in eclipse was significantly worser, and continues to be so even today, but then, eclipse isn't nearly as awesome as IntelliJ.

Therefore, I took it upon myself to preserve the awesomeness of my favourite IDE, and wrote this little plugin.

The implementation was inspired by ReSharper. ReSharper lets you hit Ctrl + Alt + F7 on a symbol, and it's usages would immediately show up in a neat little list, and selecting a uage from there would take you to the usage.
This feature is identical, except for the keyboard shortcut, which I chose to be Alt + Shift + F7, since Ctrl + Alt + F7 on Linux is already taken by X for navigating to the 7th terminal,
which usually is the primary display, i.e. the one you're probably using at the moment, and therefore it'd seem like nothing happened on hitting the key combination.

Starting verstion 7.0, IntelliJ added a built-in feature similar to this (but stupidly bound to Ctrl + Alt + F7). Therefore this plugin is obsolete amd will not be developed any further.
