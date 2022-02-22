package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.JMap;
import com.github.johantiden.adventofcode.common.Lists;

import java.util.Comparator;
import java.util.Optional;

public class A2021_10 {

    static final String EXAMPLE =
"""
[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]
""";

    static final String REAL =
"""
({<{({([<<{<[<<><>><<>>](<()()>)><{{{}<>}}<<<>{}>[<>[]]>>}{[<<[]{}>[<>()]>[(()[])]]<{{<><>}[[]<>]}{<
[(<[[([[(([<{{[]{}}([][])}<<{}[]>([]{})>><{<()()><[][]>}{[()]<()[]>}>]<{({()()}){[{}[]][<>()]}}[{[{}<>]
{{(<[[(<<<(<<[{}{}]{<>()}><{[]{}}([]{})>>([([][]){{}<>}][<()<>>[(){}]]))<{<(<>())[{}<>]>{{[]{}}<<>{}>}}({<
<([({[<[(([[<(()<>){[]<>}><<(){}>{()()}>]](<(({}[]){[][]})<{<>())[<>[]]>>{{{()<>}{(){}}}{[<>()]<{}[]>}
<(((<{<(<{{[[[()<>]{[]()}]][{<()[]><<>{}>}]}}[<{[{[][]}[<><>]]}{{{(){}}[<>()]}{[[]()]{<>[]}}}>{[({{}
{{({[({(({{[<[{}()]([]<>)><<{}()>[<>[]]>]<<{[]{}}([]<>)>{{<>}{<>{}}}}}{{<<[]<>>{<>()}>}<({[]<>}
(([{[(<(<[[(([()[]][{}()])(([][])<{}<>>))]]>)[<(<{{<<><>>(<>[])}<([]<>)[[]{}]>}((<{}[]>[<><>]){{[
{{[<<{<<(<([<<[]()>[[][]]>{[()[]]<()[]>}]){(<(<>{})([])>{<(){}>{<>()]}){[<[]()><<>()>](([][
((<{[{{(<<[(<{<>{}}[<>[]]>{<{}<>>{{}[]}})]>><{([<(<>())[{}()]>{<<>{}>{[]{}}}><([[]()]<[]<>>)(<[]>{()()})>)}>)
{<({{{(<[([<{(<>[])<()[]>}([[]]([]{}))>])[{{[([]){[]{}}]}}{[[{(){}}{<>{}}][[(){}]]]<([<>{}]{{}<>})(
([([{{{[[<<[[(()[])<<><>>]{({}[])}]((([]{})<<>>)({[]<>}[<>[]]))>{(({<>()})([{}[]]<()<>>)){[
{[<(({[<<{{{[({}[]){<>()}]{[()()]{[]()}}}[{<<><>>{<>[]}}<{()()}<()>>]}({<(<><>)[()[]]>([{}()])}{[(<>())(()
<{{[[<{{(<<{<[{}{}]<{}[]>>[{{}[]}[{}()]]}((<()()>{(){}})<((){})>)>[{<({})[[]{}]>([{}<>])}{{[{}()]}{<[
[{[<{<[[({([(<{}<>>[<>()])]<<<{}{}>({}<>)>>)<{(({}[])({}<>))}<{<[]>({}<>)}{{()()}([]())}>>}){<[<[[{}<>]<
{(<{{<{<<(<<<[{}{}]((){})>>{<<{}{}>{()[]}>}>)([({(<>())<<>()>>)(<<()[]>(<>())><<()()>({}())>)]{(<[<><
[{<{[<{([{[({<(){}>([]())}[<<>[]>([]())]){{<(){}>{{}[]}}<[()[]]({})]}](((<{}()>{{}()})))}[[(
<<[{{[<<(<{({{[]{}}{()()}}){<<{}()>({}<>)>(({}<>)[()<>])}}<[[([]{})[<><>]]]((<()()>{<>[]}){{[]{
[[({[({[([({{<[]{}>{(){}}}{[[][]][()()]}}[{<{}><{}()>}({()[]})]){{[[()<>]<[][]>][({}<>)]}}](<({<{}(
[[[((([((<{{[(<>())[{}[]]]{{<>{}}}}}>))<[[[({{{}{}}(()())})({([]{})[<><>]}[([]{}){()[]}])]<{<[{
[{[<[{{([[{(({()()}<{}[]>){{()<>}{{}()}})<<<{}[]>>{<<>[]><{}{}>}>}]{{{[{()()}[<>()}]{{{}}[(){}]}}}
[[{[(({[[[[{(<<>()>(<>()))[<{}[]>{[]<>}]}{{{()<>}<()()>}}]<[<([]<>}{<>[]}>]>](((<{()()}<()()>><<<>[]><<>
({[{({(<{((<<[()[]]<()<>>]<([][])>>[([{}()]<{}{}>){<{}{}>(()[])}])[{([<>()]{()[]}){[()[]]}}([{()<>}
[(({(([[{(<{{<[][]>(()()}}[({}[])]}[(<<>[]><()[]>)]>)<{({{[]{}}[()[]]}[<<>()>(()())])}{(((()[])<[]>)
<(<([[({[((((<{}[]>[{}{}]){[{}<>](<><>)})(([()<>]({})){[{}{}]{[]<>}}))[({(()[])<<>[]>}[[()<>]<()
{[(<<({([{[(<<[][]>(<>())>){<[<>()][[]{}]>(({}[])[(){}])}][(({{}<>}))(<[()()]<()()>>}]}{[{<(<>
[{{({<({<(<[[<{}<>>{()[]}][{()()}]]>){[<{{()[]}[{}()]}>([<()[]><{}<>>](<()[]>))]{[<[<><>]{<>[]}>[<()
{[<<[{<<[[(<(<[][]>[()()])>){<(([]()){{}[]})><<[{}()]<<>>>>}}[[([<{}[]><(){}>][({}())[()<>]])[[{<>[]
{((<[{{<{[<[((()<>)<()()>)]>]}>}((([{[[<<>[]>[[][]]]<[[]()]({}[])]]{[([]{}){()()}]}}<((<[]<
{[[({<{<<{<{{{[]{}}}}>}[[{(([][]){<>[]}){{()<>}}}<<<()[]>[{}[]]>>]<[{[()[]]({}{})}{[{}()]}]>]>>}>}<
[{[(<({{(<(([{()<>}{()<>}]{{[]}(()())]))>(<<{[<><>]<()<>>}[(<>{})<<>{}>]>[{{<>{}}{{}[]}}<[<
{{((<[(([<{{([()][(){}])({<>()}[()<>])}[{[{}<>]<()()>}<{{}}([]<>)>>}(<<[[]{}]<[]<>>>[([]()){[]{}}]>((<<>(
(<<<(<([<<({<<<><>>[{}<>]>{<()>([]<>)}}{{(<><>)}<{<>()}{{}()}>})<<<({}{})<[]<>>>>>]{({({[][]}[{}()])([<>()](
([(<<<(<(([([{<>{}}<(){}>](<{}<>>[{}{}]})][([<()>{{}[]}](<<>()><()[]>))[[{<>()}[<>[]]]{<<>[]>
(<{[[<<<({[<<([]())[()()]>([()[]]{<>{}})>]})[[{<[{<>()}({}{})><(()<>)([]{})>><<<<>()><<>()
[[[{(<[[((<[{<<>[]>({}[])}<[{}{}]{[]<>}>]<{({}{})}{[{}<>]{<><>}}>>([{[[]<>](()<>)}{{<>{}}(<>[]))]{[{{}{}}]})
[(<([[[{<[[<((()()){[][]})>((<{}[]>[()<>])<([]()){<>[]}>)]<<{<<><>>}{{(){}}}>>]<{[<<(){}>({}<>)><<<>()>([]<>
[<((<<[<{[{[{[()[]]{()<>}}(<<>()>[<>()])]}{<[[(){}]{(){}}]{[()]{<>[]}}>{{<[][]>[()()]}[(<>{
[({{([([{[[([<<>{}>[[]()]]({{}()}))][<{{()[]}}<<{}{}>>><[[{}[]][<>()]][{{}[]}(<>{}))>]][{((((){
((<<{(([[[{{<[()[]]({}())>}<[{(){}}]<({}{})<()[]>>>}{((<()<>>([]<>)){[()][()[]]})([{()}<[]{}>]<(<><>
<<<<[([<<(({<[[]()]<<><>>>({[][]})}{([<>{}]<<>()>){{[]{}}}})){[[[[[]{}][(){})]{(()[])(()<>)}]{{[[]<>][{}(
[({{([<{({{[{(()[])<<><>>}(((){})[<>()])>}{{{{{}<>}<{}[]>}{[(){}](()[])}}}}([(([<>{}](<>[]))
([(<({{(<<[([[<>][<>()]])<[{()[]}([]{})]{[()<>][[]{}]}>]><<{[{<><>}<()()>]{{<>[]}>}>>><[<{{
({{(([({{<[<[{{}{}}{{}<>}]((<>{})[[]<>])>(((<><>)<{}()>){{{}{}}{{}{}}})]({<{{}}([][])><<[]{}>
[(<{({{(<<<(<[{}[]]{(){}}>(([]()){<>[])))[{{[]{}}{()()}}{[<>{}][<>]}]>[((<{}<>>[<>[]])[({}[])<[]{}>])[<[{
[(<<<[<<({{{[[(){}]]{<<>{}>{()[]}}}{({<>{}}{[][]})}}{<([()[]]<{}<>>)([{}()]({}[]))>[(<<>()>{[]()})]}}{([{{(
{[{{(({[{<{{[[()<>]<[]<>>]({{}{}}[{}{}])}{(<<>[]>{[]})[[[]{}]{()<>}>}}{<<{{}{}}(()[])>>{{[[]()]
[<[[{(([<<[<[([][])<[]{}}]{<(){}>}>(<<()<>><[][]>><<{}[]>[{}()]>)]><([([[]<>](()[]))[(()<>)<(){}>]][({
<{{<([<([{{{(<[]{}>[[]{}])[{<>{}}([])]}}[<[<<><>>({}<>)]{((){})[[]<>]}><[{()[]}<<>{}>]{{()()
{[<<<([{([{<<(()[]){{}{}}>[(<>{}){{}()}]><[[{}<>][[]()]](<{}()>([]()))>}]){{<{<[()<>][<>()]><
<{[<<[([[{[({[[]<>](<>[])})({([]<>)<[]()>}<([]<>)[<>[]]>)]}[[{[{()<>}{<>{}}][<[]{}>(<>[])]}<({[]<>
[[[[([({(((<<[()<>]>[(()())]>{[{{}()}[[]()]]{[<>()]([][])}})))}(<({(({()[]}[<>()]))<[[()<>]]<{{}()}[()<>]
{[([([<[(<<[<[[]()]<[]<>>>][{<[][]>[<>()]}{({}<>)<()[]>}]><{({<>()}{<><>})<{(){}}({}{})>}<{(
<<({[<<{(<[<({{}[]}{<>[]})[<()()>]>(([<>()][<><>])(<<>{}><()[]>))](<[<{}<>>[{}[]]]({{}{}}(
[<<{<(([<[{(([()<>]({}{})){<<>[]>[()()]})({([])(<>())}[{[][]}[{}[]]])}{({(()())[()[]]}({[]<>}([][])))
((({<[{([(<[(<[]()>)](<[{}()]<[]{}>>(({}())<()[]>))>)[(<[([]<>)(<>{})]>)]][({({[()[]]{<>}}[[[]{}]{()[]}])([<<
[[[(({[<[((({([]){<>{}}})[[{()[]}[{}[]]][{[]<>}]])<<(<[]{}>[<>[]])[<[][]>[{}[]]]>>)[([{{<><>}[[][]]
({<(({{[{{<[((<>())(<>[]))<<<><>>((){})>]([<[][]><{}{}>][[{}()]<<>()>])>((<[[]()]([][])>[<{}[]>{
(({<<([[<[[{<[()()]<[][]}>[{(){}}[{}<>]]}(<{{}<>}{[]<>}>{<(){}>{<><>}})]<<{<<>[]>[<>[]]}{{
<[{([<[({<((<[{}()]([]<>))[(()[])])((({}{})<()<>>){(()<>)}))>{[<<[<>{}][(){}]>{<<><>>(<>[])}>{[
{[([[[{{<<[<<<()()>(()<>)>[({}{})[<>{}]]>([[[]<>]]{<<>>[[][]]})]><<[{(()[])][<()>[{}[]]]]<([{}{}][()<>])(
{(({{<<((<{<<<[]{}>{{}()}><<<>()><()[]>>><<[<>[]]](({}[])[[]{}])>}[[({<>()}{()}){[[]{}]({}())}
<[[{<[({<([<<<()<>>>>([[[]()]{<><>}](<<>[]>[<>]))])(({({[]{}}){{{}()}{(){}}}}[{(()<>)[[]()]}[{<>()}{[][]}]]))
{<[[([{<<<{{[<[]{}>[[]<>]]{({}())<[][]>}}<<<{}{}>>({(){}})>}([{({}())}<([]())[()()]>])>({{[[<>()]{{}{
<{(<[{[[{[<<<{(){}}[<>{}]>{{{}[]}<<>()>}>{[{[]()}<<>>]{<[]{}>[()()]}}>][[[{<<>())[[]<>]}<(<>
[{{<<{{([(<<{{{}}[()]}<(()())(<>())>>(<{[]()}[[]<>]>)>[((<()()><[]{}>}({[]<>}))(({[][]}{[]<>})<(()[])(()[]
([(<<[<{({[[[(()<>)<{}()>]{[{}()]{<>{}}}]{(<(){}>{{}[]}){<<><>><{}[]>}}]}<{[[[()()]{{}{}}]]
{<(((<({<((([<(){}>]){{{<><>}{{}()}}})({<[[]{}]>{({}())}}))>})([[<<<{[{}[]]{[]{}}}<{()<>}({}[])>>>{[
{([[{[[[<<<({{()<>}}(<[]<>>[<><>]))[[[[]<>][{}()]]]>{<<[<>()][[][]]](<{}{}>{[]{}})>[<(<>{}){<><>}>([{}[]]
[[{(<{{{<(<{[[{}()](()[])]}<<{(){}}[()[]]>{[()()]([]())}>>)>[(<<<{[][]}>>[{([][])[(){}]}[(<
<{[(<{<{(<<{<{()[]}(()[])>(([]<>)([]()))}>{((({}[]))<{[]()}{{}()>>)<{<(){}>}<{<>()}<()()>>
<(<[{{[[{<{[{[<>][<>[]]}]{[(<>[])][<{}{}>{<>}]}}(({[()<>]{[]<>}}<{(){}}[[]{}]>)([[<><>]<()()>]<({}[])([]())
{(<{[{[{({{(<<<>{}>><({}())(<>())>)}}{(<<<(){}>{(){}}>(<{}()>(<>()))><[{<>()}{{}()}][{[]<>}(()())]>){<[{{}[]
{[((((({<(([(<[]<>><[]<>>)<<{}()>>](<{()[]}(()<>)>{{{}{}}([]{})})))[<[{([])[<>]}(([]())<{}{}>]]{
(<{[((([{(<<{(()<>){{}[]}}>><<<({}<>)[[]()]>{(<>[])[()()]}>{{{<><>}([]())}}>)[{(([[]()][<>(
<[<<<[(<[[(<<([]{})>([[][]][{}{}])><[<<>[]>[{}[]]]<[<>][()()]>>)][[<<{()[]}(<><>)><<()()>[()<>]>>][{[{[]()
<<{([[<[[<[({[<>[]](()<>)}{({}[]){()[]}}){({{}[]}(<>()))}]<[[<[][]>[[]<>]]<[{}<>]<[]{}>>)>>]<
[((([[{<<[[<{{{}[]}[()<>]}>(<<{}{}>[(){}]>({<>[]}<{}<>>))][<{[{}<>]{[]}}>({[{}{}}[[][]]})]](({[{[][]}]{[{}
[([{<[[{<((<<{[]<>}(()<>)>>{(<<>{}>(()<>))}))[(<[[[][]][<>{}]]{[()[]](()())}><({{}{}}}[({}())]>
{{[({<(({{[({{[]()}<()<>>}{([]()){[]{}}}){[<<>>[<><>]][<{}<>><()[]>]}][{<({}{}><[][]>>{{{}()}<(){}>}}]}}))>}<
{[{[<{{({[({[[{}()][(){}]]{{[][]}<<>()>}}[[(<>[])<[]{}>]((<>[])[{}()])])]})}}>{({{<([{<([]())[{}{}]>{
<{{[{<{<<{{({<{}[]>[(){}]}[[<>()]{(){}}])}}[{{[(()<>)(<><>)]<{()<>}{<>()}>}{({[]()}{{}()})<{{
((((([{{<<[<({()<>}<<>{}))<{{}<>}(<>[])>>{<{(){}}[{}[]]>}]{<<[{}{}]>{{()()}[{}<>]}>}>>([[({(()())<()
(({{[[<[[<<<<<[]()>[{}<>]]>>({[<<><>><{}()>]{{(){}}[<>{}]}}[[([]<>)({}<>)]])>{<[[<{}[]><{}<>>][<<>[]>]](<<[]
<[<([<<[([[[[<{}[]><()>][(<>{}){[]}]]{({()[]}[<>{}])}]([((<>[])(<>[]))[{<>[]}{[]<>}]][(([]{})({}<>
<((<{{({{([<([<>{}](()()))<<(){}>[<>[]>>>({<<>>([]{})})]<[[([]())<<>>]{{<>{}}(()<>)}]>)<[(((<>)<{}<>>)({<>(
{({<({<<[[{[((<>())<()<>>)<(<>[])((){})>]({{[]()}[{}()]}[[{}{}]<{}[])])}({<[(){}](<><>)><{()<>}[{}[]
<[(<<[((([(([[[]<>][<><>]]{{{}{}}[{}[]]}){(({}())<[]{}>)})<(<(()[]){()()}>{<()>[[]{}]}){{[[]{}]([]<
{(<(([{[[<[[[{<>{}}{[]{}}]({(){}})]<[[()()]][[{}[]]]>]{<<{{}{}}[{}{}]>>[(<{}()>([]{}))(<(){}>{<><>}
((<<({({<{<<[<[]{}><()[]>]<(<>()){()[]}>>>}{(((({}<>)({}()))<{[]{}}<<>[]>>)<({[][]}<(){}>){
[[<{((({{{(<{[[]{}}{{}}}<{[]{}}<()>>>{<{{}[]}<[]()>>{[()[]]{[][]}}})([{[[][]]}(([]{})<<><>>)][(<[][]>{{}
(<({[{[(<<(((([]())[<><>]}([<><>]))<[[()<>]<<><>>]{<[]()>{()<>}}>)[<<{[]()}(())>><[<(){}><<>{}
(<<[<(<{<({<[{(){}}{<><>}][<<>()>]>}<[<[()[]]<{}[]>>]<<({}[]]><[[][]]<[]()>>>>)>}><[((<[<<[]<>>>[[<>()][{}(
[((<[{[[([{[[<<>>{<>()}]<[[]{}]<[]<>>>]}<({(()<>))<<<>()><<>[]>>)<{<[]{}>((){})}[({}[]){()()}]>
<<{{({{([{{<<{{}{}}(<>{})><{()()}[{}<>]>><{<[]<>>[[]()]}[<<><>>({})]>}(((<<>{}><{}{}>)([<>{}][{}]))[{
""";

    static final JMap<Character, Integer> VALUE_FOR_CORRUPT = JMap.<Character, Integer>empty()
            .with(')', 3)
            .with(']', 57)
            .with('}', 1197)
            .with('>', 25137);

    static final JMap<Character, Integer> VALUE_FOR_AUTOCOMPLETE = JMap.<Character, Integer>empty()
            .with(')', 1)
            .with(']', 2)
            .with('}', 3)
            .with('>', 4);

    static long a(String input) {
        return JList.ofArray(input.split("\n"))
                .map(A2021_10::findScore)
                .reduce(Integer::sum);
    }

    static long b(String input) {
        return JList.ofArray(input.split("\n"))
                .filter(row -> findCorruptedCharacter(row).isEmpty())
                .map(A2021_10::reduceAsFarAsPossible)
                .map(A2021_10::findClosingSequence)
                .map(A2021_10::score)
                .median(Comparator.naturalOrder());
    }

    static long score(JList<Character> sequence) {
        return score(0L, sequence);
    }

    private static long score(long score, JList<Character> sequence) {
        if (sequence.isEmpty()) {
            return score;
        } else {
            return score(score * 5 + VALUE_FOR_AUTOCOMPLETE.getOrThrow(sequence.head()), sequence.tail());
        }
    }

    private static JList<Character> findClosingSequence(String incompleteReduced) {
        JList<Character> characters = Lists.charactersOf(incompleteReduced);
        return Lists.range(incompleteReduced.length()).reversed()
                .map(characters::get)
                .map(A2021_10::opposite);
    }

    private static Character opposite(Character c) {
        return switch (c) {
            case '<' -> '>';
            case '{' -> '}';
            case '(' -> ')';
            case '[' -> ']';
            default -> throw new IllegalStateException();
        };
    }

    private static Integer findScore(String row) {
        return findCorruptedCharacter(row)
                .map(VALUE_FOR_CORRUPT::getOrThrow)
                .orElse(0);
    }

    static Optional<Character> findCorruptedCharacter(String row) {
        return findCorruptedCharacterOnReducedString(reduceAsFarAsPossible(row));
    }

    private static Optional<Character> findCorruptedCharacterOnReducedString(String reducedString) {
        String withoutOpeners = reducedString
                .replaceAll("<", "")
                .replaceAll("\\(", "")
                .replaceAll("\\{", "")
                .replaceAll("\\[", "");

        if (!withoutOpeners.isEmpty()) {
            return Optional.of(Lists.charactersOf(withoutOpeners).head());
        } else {
            return Optional.empty();
        }
    }

    private static String reduceAsFarAsPossible(String row) {
        String withoutEmpties = row
                .replaceAll("<>", "")
                .replaceAll("\\(\\)", "")
                .replaceAll("\\{\\}", "")
                .replaceAll("\\[\\]", "");

        if (!withoutEmpties.equals(row)) {
            return reduceAsFarAsPossible(withoutEmpties);
        } else {
            return row;
        }
    }

}