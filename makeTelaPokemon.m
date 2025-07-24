function TelaPokemon = makeTelaPokemon(Teladefiningspecies,TelaStatsTypesAbilities,Telamonslevelupmoves,Teladexentries)
    pokemon = Teladefiningspecies(1:51,1);
    k=1;

    for i=1:size(pokemon)
        disp(pokemon(i,1))
        [rSTA,cSTA] = find(TelaStatsTypesAbilities==pokemon(i,1));
        [rS,cS] = find(Teladefiningspecies==pokemon(i,1));
        [rE,cE] = find(Teladexentries==pokemon(i,1));
        [rM,cM] = find(Telamonslevelupmoves==pokemon(i,1));
        moves="?!?"; eggMoves = "?!?";
        for j=2:27
            if ~strcmp(Telamonslevelupmoves(rM+j,2),"")
                moves = moves+","+Telamonslevelupmoves(rM+j,1)+","+Telamonslevelupmoves(rM+j,2);
            end
            if ~strcmp(Telamonslevelupmoves(rM+j,3),"")                          
                eggMoves = eggMoves+","+Telamonslevelupmoves(rM+j,3);            
            end
        end

        TelaPokemon(k,1) = "#-------------------------------  =";k=k+1;
        TelaPokemon(k,1) = "["+Teladefiningspecies(rS,1)+"]";k=k+1;
        TelaPokemon(k,1) = "Name = "+Teladefiningspecies(rS,2);k=k+1;
        TelaPokemon(k,1) = "Types = "+upper(TelaStatsTypesAbilities(rSTA+1,1)+","+TelaStatsTypesAbilities(rSTA+2,1)); k=k+1;
        TelaPokemon(k,1) = "BaseStats = "+TelaStatsTypesAbilities(rSTA+6,1)+","+TelaStatsTypesAbilities(rSTA+7,1)...
                                        +","+TelaStatsTypesAbilities(rSTA+8,1)+","+TelaStatsTypesAbilities(rSTA+11,1)...
                                        +","+TelaStatsTypesAbilities(rSTA+9,1)+","+TelaStatsTypesAbilities(rSTA+10,1); k=k+1;
        TelaPokemon(k,1) = "GenderRatio = "+Teladefiningspecies(rS,3);k=k+1;
        TelaPokemon(k,1) = "GrowthRate = "+Teladefiningspecies(rS,4);k=k+1;
        TelaPokemon(k,1) = "BaseExp = "+64*str2num(Teladefiningspecies(i,5));k=k+1;
        TelaPokemon(k,1) = "EVs = "+Teladefiningspecies(rS,6);k=k+1;
        TelaPokemon(k,1) = "CatchRate = "+Teladefiningspecies(rS,7); k=k+1;
        TelaPokemon(k,1) = "Happiness = "+Teladefiningspecies(rS,8);k=k+1;
        TelaPokemon(k,1) = "Abilities = "+upper(strrep(TelaStatsTypesAbilities(rSTA+3,1),' ' ,''))+","+upper(strrep(TelaStatsTypesAbilities(rSTA+4,1),' ','')); k=k+1;
        TelaPokemon(k,1) = "HiddenAbilities = "+upper(strrep(TelaStatsTypesAbilities(rSTA+5,1),' ' ,'')); k=k+1;
        TelaPokemon(k,1) = "Moves = "+upper(strrep(moves,' ' ,''));k=k+1;
        %TelaPokemon(k,1) = "TutorMoves = "+;k=k+1;
        %TelaPokemon(k,1) = "EggMoves = "+upper(strrep(eggMoves,' ',''));k=k+1;
        TelaPokemon(k,1) = "EggGroups = "+Teladefiningspecies(rS,9); k=k+1;
        TelaPokemon(k,1) = "HatchSteps = "+Teladefiningspecies(rS,10); k=k+1; if strcmp(Teladefiningspecies(rS,10),"0") k=k-1; end
        TelaPokemon(k,1) = "Height = "+Teladefiningspecies(rS,11); k=k+1;
        TelaPokemon(k,1) = "Weight = "+Teladefiningspecies(rS,12); k=k+1;
        TelaPokemon(k,1) = "Color = "+Teladefiningspecies(rS,13); k=k+1;
        TelaPokemon(k,1) = "Shapes = "+Teladefiningspecies(rS,14); k=k+1;
        TelaPokemon(k,1) = "Category = "+Teladefiningspecies(rS,15); k=k+1;
        TelaPokemon(k,1) = "Pokedex = "+Teladexentries(rE,2); k=k+1;
        TelaPokemon(k,1) = "Evolutions = "+Teladefiningspecies(rS,16); k=k+1; if strcmp(Teladefiningspecies(rS,16),"0") k=k-1; end    
    end
    TelaPokemon = strrep(TelaPokemon,"?!?,",'');
    TelaPokemon = strrep(TelaPokemon,"INFILTRAITOR","INFILTRATOR");
    TelaPokemon = strrep(TelaPokemon,"EggMoves = ?!?","EggMoves = ");
    
    TelaPokemon = array2table(TelaPokemon);
    writetable(TelaPokemon,'C:\Users\User\Desktop\my anime\Pokemon Tela Region\organizing data\MATLAB - build pokedex\TelaPokemon.txt','WriteVariableNames',false,'Delimiter','space','QuoteStrings','none')

end