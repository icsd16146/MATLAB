function NLP()
    redditPosts = importData("C:\Users\konstantina\Desktop\MATLAB\diplomatiki\nlp\redditPosts.txt");%κατεβασμα δεδομενων
    redditCreatedutc = importData("C:\Users\konstantina\Desktop\MATLAB\diplomatiki\nlp\redditCreated_utc.txt");%κατεβασμα δεδομενων
    
    [sortedPosts,sortedDates] = sortPosts(redditCreatedutc,redditPosts);%ταξινομηση των Post βαση αριθμο δημιουργιας
    %να εχουν γραφτει στο command τα εξης:
    %sortedDates=[1;2]
    %sortedPosts=["1";"2"]

    tokenPosts = Cleaning(sortedPosts(:,1));%καθαρισμος και tokenization
    %να εχουν γραφτει στο command τα εξης:
    %tokenPosts=[]
    cleanPosts=joinWords(tokenPosts);%μετατροπη σε string array
    %documents = preprocessText(cleanPosts);

    dictionary = CreateDictionary(cleanPosts);%δημιουργια λεξικου
    numOfInstances = findOccurrencies(dictionary,cleanPosts);%ευρεση αριθμου εμφανισης καθε λεξης
    [sortedDictionary,sortedNumInst] = sortPosts(numOfInstances,dictionary);%ταξινομηση κατα φθινουσα σειρα

    WordCloud(dictionary);

    %define positive&negative seeds
    seedsPositive = ["activism" "organic" "endanger" "preserve" "pollution" ...
    "ocean" "natural" "flood" "mechanism" "homeless" "research" "complex" ...
    "agricultural"]';

    seedsNegative = ["skeptic" "pity" "debunk" "hide" "hoax" "institution"...
    "conservative" "scam" "propaganda" "critic" "exaggerate"]';

    lexicon = createSentimentLexicon(tokenPosts,seedsPositive,seedsNegative);%ΔΕΝ ΛΕΙΤΟΥΡΓΕΙ

    sentimentScores = vaderSentimentScores(tokenPosts,'SentimentLexicon',lexicon);

    periods= 126:166;%οριο χωρισμου ημ/νιων
    timeTable = ScoresxTime(sortedDates,sentimentScores,periods);
    plot(1:length(timeTable),timeTable);

end