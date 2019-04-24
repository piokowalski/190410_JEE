# JJDD6 Prace domowe (JEE)

## Zasady

1. Praca domowa powinna zostać zrealizowana w ramach projektu tworzonego na 
zajęciach, w branchu utworzonym na zajęciach.
2. Rozwiązanie powinno znaleźć się w repozytorium maksymalnie 
o godzinie startu warsztatów dnia którego następuje jego weryfikacja.
3. Praca domowa z pojedynczych zajęć to 1-2 zadania punktowane 1-2pp. 
4. Warunkiem otrzymania punktów jest zrealizowanie poprawnego, 
stabilnego rozwiązania oraz poddanie się publicznemu review 
wraz prezentacją i omówieniem własnego kodu.
5. W przypadku większej liczby chętnych:
   * Pierwszeństwo mają osoby, które wcześniej nie prezentowały żadnego rozwiązania
   * Jeśli chętnych jest więcej niż jedna osoba, następuje imienne losowanie 
  z wykorzystaniem `https://onlinerandomtools.com/shuffle-lines`
   * Wyjątkowo dobre rozwiązania mogą zostać nagrodzone mimo braku prezentacji 
  publicznej, pod warunkiem, że prezentacja nie odbyła się z powodu braku czasu
6. Zadania muszą być realizowane samodzielnie. 
7. Prace domowe nie są obowiązkowe.


## Zadania

Do wszystkich zadań należy wykorzystać wybrane poznane mechanizmy JEE 
(Servlety, EJB, CDI, Interceptory, Filtry, FM/FTL).

1. Przygotuj servlet który w kontekście webowym `/user` wyświetli wszystkich 
użytkowników z repozytorium na jedynym widoku.
   	Każda pozycja na liście powinna wyświetlać wszystkie dostępne informacje 
   	o użytkowniku.

    | Punktacja        | Termin           |
    | :-------------: |:-------------:|
    | 1 p.p.      | 17.04.2019 |
    
2. Rozwiąż zadanie 1 oraz do każdej pozycji użytkownika dodaj link „delete user”, 
którego kliknięcie spowoduje skasowanie użytkownika z bazy danych. 
Kasowanie powinno zostać zaimplementowane w kontekście webowym `/user`.

   | Punktacja        | Termin           |
   | :-------------: |:-------------:|
   | 2 p.p.      | 17.04.2019 |
   
3. Listę użytkowników przepisz na postać używającą FM. Dodatkowo przenieś formularz 
z pliku add-user.html do servletu z FM. Plik add-user.html powinien zostać skasowany
z drzewa aplikacji, jednak funkcjonalność dodawania użytkownika powinna zostać zachowana.
Dodawanie powinno zostać zaimplementowane w kontekście webowym `/user`.

    | Punktacja        | Termin           |
    | :-------------: |:-------------:|
    | 1 p.p.      | 24.04.2019 |
    
4. Rozwiąż zadanie 3 oraz do każdej pozycji użytkownika dodaj link „edit user”, 
którego kliknięcie spowoduje przekierowanie na nowy widok edycji użytkownika.
Na tym widoku powinien wyświetlać się dokładnie ten sam formularz co przy dodawaniu
użytkownika z tą tylko różnicą, że pola formularza będą domyślnie wypełnione aktualną
wartością (atrybut `value`). 
Edycja powinna zostać zaimplementowana w kontekście webowym `/user`.

   | Punktacja        | Termin           |
   | :-------------: |:-------------:|
   | 2 p.p.      | 24.04.2019 |
   
5. Przygotuj dowolną grafikę maksymalnego rozmiaru 200x200px. Może to być po prostu
czarny kawałek obrazka. Potraktuj tę grafikę jako zaślepkę: jeśli zdjęcie użytkownika 
nie istnieje, umieść domyślnie tę grafikę. Do rozwiązania wykorzystaj filtry.
Jeśli zapisywany użytkownik posiada zaślepkę, zaloguj ten fakt interceptorem w czasie
operacji zapisu, podając w logach wszystkie znane informacje o użytkowniku. 

   | Punktacja        | Termin           |
   | :-------------: |:-------------:|
   | 1 p.p.      | 25.04.2019 |
   
6. Przygotuj w pełni stabilną i bezpieczną aplikację umożliwiającą dodawanie, edycję,
kasowanie użytkownika. Zapewnij pełną walidację danych oraz komunikaty dla użytkownika.

- dodawanie: wszystkie pola, poza obrazkiem - obowiązkowe, obrazek powinien być ustawiany 
domyślny (zad 5), ID dodawanego usera powinno być unikalne, po dodaniu usera widok
powinien przekierować na listę użytkowników i wyświetlić tam komunikat o powodzeniu 
operacji. Jeśli dodawanie się nie udało, widok powinien pozostać na formularzu
dodawania użytkownika, z komunikatem błędu oraz polami formularza wypełnionymi 
przed wystąpieniem błędu (nie chcemy męczyć użytkownika ponownym wypełnianiem danych)
- edycja: ID nie jest możliwy do zmiany, jeśli hasło nie jest podane to nie podlega 
edycji, warunki walidacji analogiczne do dodawania użytkownika wraz z przekierowaniem 
po sukcesie edycji, jeśli zdjęcie nie zostało załadowane, pozostaje to poprzednie, 
jeśli zdjęcie zostało załadowane - następuje jego podmiana
- brak specyfikacji walidacji, należy przewidzieć potencjalne problemy i skutecznie
przed nimi się zabezpieczyć

   | Punktacja        | Termin           |
   | :-------------: |:-------------:|
   | 3 p.p.      | 09.05.2019 |
   