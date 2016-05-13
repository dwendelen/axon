1 USER AGGREGATE

A user has a name and an email address.
It is possible to register a user and to update his/her email address.

Complete the classes to implement these features.
All tests should run green at the end of this exercise.

Tips:
 - @AggregateIdentifier
 - @TargetAggregateIdentifier


2 EVENT LISTENERS

When a user registers, a mail is sent to the new users email address.
There also needs to be send a mail when the email address is changed.
It is allowed to use the same message for both cases.

Create a new listener that calls the mail client in Application.
Also try to create a unit test for the listener.
All tests should run green at the end of this exercise.

Tips:
 -  MailClientMock


3 READ MODELS

The user can now use a new shell to login and change his/her email address.
To accommodate these features, read models will be used by the UI. For the
available commands in the new shell see SHELL-COMMANDS.txt or run 'help'.

Because we will be using read models, the Application no longer provides an
interface to fetch a user.

There is also virtual mail server with all sent emails. This mail server is not
part of the domain and is already implemented.

Create the necessary listeners in the UI, and register them with the application.
These listeners should keep the DTO's in the UI in sync. Classes in the UI to
change are in the package 'axon.ui.user'.
All tests should run green at the end of this exercise.

Tips:
 - Register the listener by Autowiring the Application in the listener and
    register itself it in a @PostConstruct annotated method.


4 SPRING

Optional exercise. Functionality remains the same.

Convert the core of the application to spring. Create a separate xml for the
application and include this xml in spring-shell-plugin.xml. Register the
UI-listeners in spring-shell-plugin.xml and delete Application.registerListener().

Fix all tests. In ApplicationTest the Application and MailCientMock should be
autowired into the test.

Tips:
 - http://www.axonframework.org/docs/2.4/using-spring.html
 - Turn MailClientMock into a spring bean. This bean must be the primary bean.


5 SAGAS

Now we add games to the system. A User can buy games and they can buy a game
only once.

Users can also link a Steam account to their account. When another account is
linked to the users, then the previous one is overwritten.

When a user purchases a game, then Steam will be notified. If however no Steam
account was linked to the user at the time of the purchase, then Steam will be
notified after an account was linked. When users link their account to another
Steam account, then the next games that will be bought, should be linked to
that new Steam account.

Part 1: Create Game aggregate:
Complete the implementation of the classes in the packages:
 - axon.core.game
 - axon.ui.game
Implement the following controller method:
 - axon.ui.commands.GameCommands.registerGame()
Do not forget to reconfigure Axon in 'spring-application.xml'

Part 2: Buying games:
Implement the commands and events that have to do with buying games. Note that
the commands must be routed to the User, and not to the game.
Also implement the following controller method:
 - axon.ui.commands.UserCommands.buyGame()

Part 3: Linking Steam accounts:
 - Create commands and events the link a Steam account to a user
 - Implement axon.core.SteamIdLookup. Create event listeners to populate
    SteamIdLookup. You can also implement the listeners in SteamIdLookup.

Part 4: Notifying Steam:
Complete axon.core.SteamRegistrationSaga:
 - There should be one saga instance per game purchase, generate unique
    id's for every purchase
 - Make sure that the saga ends when Steam is notified
 - Use the SteamIdLookup to find the SteamId