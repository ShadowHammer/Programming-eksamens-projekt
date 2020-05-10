# Programming-eksamens-projekt

For at få programmet til at virke skal man først gå ind i koden og ændre under DatabaseHandler.java. Der står på linje 48: String url = "jdbc:sqlite:C:/..."
Der skal man ændre det til at være den fulde sti til filen "DataStorage.db".


Logins:

Elev:
User: Jeg
Pass: 4321

Lærer:
User: Hej
Pass: 1234

For at bruge programmet skal man først logge ind med et af de 2 logins ovenover. 

Hvis man er logget ind som lærer kan man bruge knappen "Tilføj aflevering", hvor man kan skrive i 4 tekst felter. Hvis man hold sin mus hen over tekstfelterne står der også hvordan man skal skrive teksten ind. Når man klikker på "Opret" knappen kan det se ud som om at der ikke sker noget siden at vinduet ikke lukker sig ned, men en af aflevering er blevet sendt til databasen. Desvære har vi ikke fået tilføjet at programmet opdatere sig live med afleveringer så man skal lukke programmet og åbne det igen for at se den nye aflevering.

Hvis man klikker på fanen "Afsluttede afleveringer" kan man se at der er en knap der hedder "Download aflevering" gør at den markeret aflevering i skemaet og så skal man skrive stien hen til hvor man gerne vil have besvarelserne til at lægge.



Hvis man er logget nd som elev har man 2 knapper til rådighed "Hent beskrivelse" og "Aflever", har skal man klikke på den ønskede aflevering og derefter trykke på en af de 2 knapper.
Hvis man trykekr "Hent beskrivelse", skal man i feltet skrive stien hent til der hvor den skal downloades til.
Hvis man trykker "Aflever", skal man skrive ens navn og stien til den fil som man gerne vil aflevere.
