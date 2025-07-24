function newStatsTypesAbilities = newStTyAb(StatsTypesAbilities,listNatDexPokemon)
%αποθηκευση των stats, τυπο και abilites καθε χρησιμοποιουμενου ποκεμον
newStatsTypesAbilities=[
    "0","1","2","3","4";
    "1","1","2","3","4";
    "2","1","2","3","4";
    "3","1","2","3","4";
    "4","1","2","3","4";
    "5","1","2","3","4";
    "6","1","2","3","4";
    "7","1","2","3","4";
    "8","1","2","3","4";
    "9","1","2","3","4";
    "10","1","2","3","4"];
k=1;
for i = 1:12:size(StatsTypesAbilities)                                    %για καθε γραμμη στο αρχειο levelUpMoves
    pokemon = StatsTypesAbilities(i,2)
    if pokemon == ""
    else
        isIt = sum(contains(listNatDexPokemon,pokemon))
        if isIt
            disp('true')
            newStatsTypesAbilities(k,:) = StatsTypesAbilities(i,:);
            newStatsTypesAbilities(k+1,:) = StatsTypesAbilities(i+1,:);
            newStatsTypesAbilities(k+2,:) = StatsTypesAbilities(i+2,:);
            newStatsTypesAbilities(k+3,:) = StatsTypesAbilities(i+3,:);
            newStatsTypesAbilities(k+4,:) = StatsTypesAbilities(i+4,:);
            newStatsTypesAbilities(k+5,:) = StatsTypesAbilities(i+5,:);
            newStatsTypesAbilities(k+6,:) = StatsTypesAbilities(i+6,:);
            newStatsTypesAbilities(k+7,:) = StatsTypesAbilities(i+7,:);
            newStatsTypesAbilities(k+8,:) = StatsTypesAbilities(i+8,:);
            newStatsTypesAbilities(k+9,:) = StatsTypesAbilities(i+9,:);
            newStatsTypesAbilities(k+10,:) = StatsTypesAbilities(i+10,:);
            newStatsTypesAbilities(k+11,:) = StatsTypesAbilities(i+11,:);
            k=k+12;
        end
    end
end
end