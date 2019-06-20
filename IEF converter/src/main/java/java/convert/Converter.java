package java.convert;
/**
        Ieder bestand bevat informatie van één of meer klanten en één of meer vacturen. Het bestand wordt
        opgebouwd uit verschillende soorten lines.
        1. Bedrijfsinformaties line
        2. Klant line
        3. Factuur line
        4. Factuur regel line.
        5. Factuur tekst regel.
        Van de regel 1 bevat ieder bestand slechts één regel. Van de overige drie zijn er mogelijk meerdere in
        de volgende ordering:
        - Per bedrijfs line kan er één of meerdere klant lines zijn.
        - Per klant line is er altijd één of meerdere invoice lines
        - Na iedere invoice line volgt er atijd één of meerdere factuur regel lines.
*/
public class Converter {

}
