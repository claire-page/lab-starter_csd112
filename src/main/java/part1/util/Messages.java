package part1.util;

public class Messages {

    private static final String[] nouns = {
            "blarf","snizzle","grumblet","flonker","plim","drabble","quorven","sprocket","twindle",
            "gronch","wibble","clornt","frindle","snorx","gumple","trazzle","plunket","drimble","blorv",
            "crapple","snibble","frab","glorpt","twizzle","drabblet","plompy","snorp","blimmet","crindle",
            "wobber","flimsy","gronkle","sprottle","drant","plazzle","snorblet","climsy","glorpin",
            "tinch","frabble","snockle","blimpt","drindlef","plorven","crumplet","gribblex","snorv",
            "flobbet","twomble","druff","plimmet","snibblet","glomple","cronk","frabblet","wobblex",
            "snuzzle","drimmet","plorble","flunket","twibber","grindle","snorvex","blibbet","crumblet",
            "plimblef","crabbler","snorlax","flobbin","tworble","glimmet","crabblef","snibbet","drindleq",
            "plorpt","frimble","wobbet","snarv","grumplet","blorven","dribblet","plimmetx","snobble",
            "trink","clorble","glibbet","drax","plorvenf","snopple","frimmet","grundle","blillef",
            "tworpt","driblix","plobbly","snarfet","balab","bumboo","shoofly","snazerd","namdadoo","bionofla",
            "dinobum","flimflam","wobblet","gronchly","twimble","drabblex","plorpt","snibbetty","frabbletous",
            "pridget", "dop"
    };
    private static final String[] adjectives = {
            "florbin", "snazzish", "grimbish", "plonky", "drabbletive", "tworptish", "blint", "snorvish", "clorpine",
            "frimbley", "glompt", "twindleful", "plimsyish", "drimmetive", "snobbly", "flibberous",
            "grundleful", "twabbleish", "snorptastic", "drindleish", "orvenous", "blarfish", "frabbleous",
            "twibleful", "glittive", "draxish", "plobbish", "snarfetive", "clibbleous", "flomperish",
            "gronchish", "twimbley", "drilleous", "plorptastic", "snibbetive", "floxxery", "twindleful",
            "glimplyish", "drabbletive", "plimmetous", "snoplish", "blabbleous", "tworbleish",
            "quishish", "imblefous", "snobblish", "frambleous", "glimbletive", "twimbleish", "drabblexous",
            "plorvenfous", "snollish", "flunketive", "grundleous", "twibbleous", "quonkish", "plorptivous",
            "flonky", "clorblous", "flimsive", "noseous", "ondic", "sundlic", "plimbly", "fliptly",
            "frambletive", "glimbleous", "twimbleyish", "drabbletous", "flumbly", "snibbetish", "blorptous",
            "flobberous", "krumpy", "flumply", "drimmetous", "hungrish", "snortif", "clorpish", "frimmetous",
            "pilverish", "pilver", "smorange", "fluffly", "micey", "prundy", "prundelumpicky", "poodladle",
            "flunkychunky", "incumaneroneous"
    };
    private static final String[] verbs = {
            "flomped","snazzled","grindled","plonked","dribbed","quarpled","twindled","frabbled","glorped",
            "snorkled", "blimptied","crabbled","drindled","plorpied","snibbed","flabbled","grumpled","tworbled",
            "drimmed","plommed", "snorped", "blibbed","crundled","frimmed","twabbled","glimmed","snabbled",
            "drabbled","plorvified","snocked","flibberied","gronkled", "twimmed","drabbed","plimmed","snarfed",
            "clibbled","glommed","dratched","plazzed","snobbed","flobbed","twibbled", "gribbled","druppimmed",
            "plorbled","snabbletted","frambled","glimmetted","tworped","drabixled","gromified", "snorved",
            "clorphed","flibbited","grundled","twiggified","quinted","harbinged","snopped","blabbled","frimbled",
            "twommed","drimbled","plobbed","snuzzled","glimbled","cratched","donked","farshed","shank","timbled",
            "wishered","gronched","glumbled","plornicked","shnapped","kalumphed","clorbled","glorpinned",
            "runched", "clunched","qualled","snobbled"
    };
    private static final String[] adverbs = {
            "glimply","snorvily","frabbly","twimbly","plorvishly","drimmetly","blorvenly","snabbly","flombly",
            "grindly","plimsily","drubly","tworptly","snorpily","clorptly","tonkly","inkly","twonkly",
            "zippily","urkishly","plobbly","frimmetly","blimptly","snarfily","glorpingly","warkingly",
            "quinkly","gwertly","rumptly","chiptly","flobbishly","gronchily","twimly","drimly",
            "plazzily","snibbetly","frabbletly","tworbly","glimmetly","ximptly","plimmetly","snoply",
            "libretly","ermphly","drindlequishly","plimblefously","snobblishly","frambleously","trivvenly",
            "hinderly","shustly","frustly","snoppleously","flunketly","grundlely","twibly",
            "drabbletly","imphishly","snabbletly","clorbly","flimsivly","gronkleously","intertriptly",
            "jingeously","plimbly","skobbishly","frambletively","glimbleously","twirptily","drabbletously",
            "plorvenously","snibbetishly","blorptously","flobberously","twindleishly","grumpletively","drimmetously",
            "plimmexously","snortishly","clorpishly","frimmetously","tworbleously","drimbleishly","plobbly","snuzzleously",
            "blimblefously","flimptly","ixily","squintiferously","skronteously"
    };

    private static String getRandomElement(String[] arr) {
        int index = (int)(Math.random() * arr.length);
        return arr[index];
    }

    public static String getRandomMessage() {
         return "A " + getRandomElement(adjectives) + " " + getRandomElement(nouns) + " " + getRandomElement(adverbs) + " " + getRandomElement(verbs) + ".";
    };
}
