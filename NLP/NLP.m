function NLP()
    %κατεβασμα δεδομενων
    redditPosts = importData("C:\Users\konstantina\Desktop\MATLAB\diplomatiki\nlp\redditPosts.txt");
    redditCreatedutc = importData("C:\Users\konstantina\Desktop\MATLAB\diplomatiki\nlp\redditCreated_utc.txt");

    %διαγραφη επαναλαμβανομενων ποστ
    [postsSingled,datesSingled] = killDuplicates(redditPosts,redditCreatedutc);

    %ξεχωριζει τα αγγλικα post και τα αποθηκευει
    [engPosts,dates] = extractEngTxt(postsSingled,datesSingled);

    %ταξινομηση των Post βαση αριθμο δημιουργιας
    [sortedPosts,sortedDates] = sortPosts(dates,engPosts);

    %καθαρισμος και tokenization
    tokenPosts = Cleaning(sortedPosts);

    %μετατροπη σε string array
    cleanPosts=joinWords(tokenPosts);
    %tokenPosts = preprocessText(cleanPosts);%μετατροπη σε tokenized doc

    dictionary = CreateDictionary(cleanPosts);%δημιουργια λεξικου
    numOfInstances = findOccurrencies(dictionary,cleanPosts);%ευρεση αριθμου εμφανισης καθε λεξης
    [sortedDictionary,sortedNumInst] = sortPosts(numOfInstances,dictionary);%ταξινομηση κατα φθινουσα σειρα

    WordCloud(dictionary);

    %define positive&negative seeds και import ως αρχεια(seedsPositive,
    %seedsNegative)

    lexiconWords = [seedsPositive;seedsNegative];
    lexiconScores = createSentimentScores(length(seedsPositive),length(seedsNegative));

    lexicon = createLexicon(lexiconWords,lexiconScores);

    sentimentScores = vaderSentimentScores(tokenPosts,'SentimentLexicon',lexicon);

    periods= 126:166;%οριο χωρισμου ημ/νιων
    timeTable = ScoresxTime(sortedDates,sentimentScores,periods);
    plot(1:length(timeTable),timeTable);

    Prediction(timeTable,3,4,12)

end