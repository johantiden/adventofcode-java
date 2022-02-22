package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.JList;
import com.github.johantiden.adventofcode.common.Lists;
import com.github.johantiden.adventofcode.common.Matrix;
import com.github.johantiden.adventofcode.common.Pair;

import java.util.function.BiFunction;
import java.util.function.Function;

public class A2021_09 {

    static final String EXAMPLE =
"""
2199943210
3987894921
9856789892
8767896789
9899965678
""";

    static final String REAL =
"""
4210129998765678999876598999987654567899891099876534567899989543458910998765432101234567999965412399
4321298987674567998965467898996543478998789987654323478998775412367899879987654332345699889894323678
5734987794543678987654356987987656567897678998943214589987654323456789767899767543456987678789434567
7649876543212347898765567896598767678976567899876323678998775454868997655459878654569876545678946798
8756989652103456999898678966459878989785456989965456789999976565699398743234998769698987234789656789
9897898773212367895939989656346989799654347778976587997896989876989239874123459878987654345678987899
5998999765323456794329896543235697678965265667897678956795492989878949965012345989698766456789298979
4569899875454567895998765432149989567892123456998789545689321298769898974323456795439877567892109765
3456789976765698989899896543298778478989234587899896434578910987656767895434569894323998679999399854
2348999987878799674789987654987652345678945679999989323769329876645456789545998993212349789898987943
1237898798989898523698998765699541234567896789989878910156934985432345678959876989901239899787896532
0146987659799987434567899878798632789678987899876769891347899876543456789098765676899345998656989421
9236797545678976545679999989987654578989298998765656789456998987654967892129894345678959987945679932
8945986534569897898798989999999867689490129769854347898967897998767898943234985566789998796734568893
7899877323456789939987878999899878799321298979543256967998956899878999854349876789895987655623467789
6798765494997894323976767898765989898932987898954123456789548999989398767959987899954398543212345693
5439876989889989439885456789884393957893496897893245689895434598791239879898998989993298652103456932
6721989878765678998764347899763212348954985766954766789954323498652347998787899878889098753244767899
7839999767674599987653234998654343456799874345899878999893212399743456987656789765678999874555688978
8998998654523489995432123799876654567899965456789989876789333987654569898545678954567898765668799569
9987999943212678976543012678988765678989878967899898765678945998987698765434567893678929976779893458
9876899852104567895432134569999979789876989878999765434567899899998799432123456912389019897899954567
8765498743213478976576545678989898999765496989987654323458998789999987545435867895469198789949876678
9894398654324567897687678899878767897654345699998743512349987677893297676566798986578987699432989789
9989209765434678998798799998767656789543234567899832101598765456789198987678899987679598568921299897
9879919876789999999899989899854547678932123656789999212359896767893239998789954598793459779990987956
7656899987898787899999876789543234567891012345678988923498987878989349879899543459892349889989876543
6546789999987656789998765678932123679942123759789467894987998989678998765998954567921345999878965432
5435678998976545878989654569541014567893245678995349999876549394569987654767895678990459878967896543
4326789587896434769876323678932123456789348789101278998765432123458998743456789789989998767856789654
5734795456789323557965412367943234567895469895432567899977543012367899854567899899878899854345678969
6545694387678912349876743458954345678976578976543458999876532123457998769678945998765798743234567898
7656789276569101234987856567895466789987689987654567893987643235579899898789434987674987654347698987
8767894123478912345698967678976897993298791299867978932199654356798789939994329896543498767859789436
9898943234567893456789879889987898989109890123999899541098765767987689929899459765432349878969894325
3999654345698954568995989999998999878998943294986789652129896979896567898798999985321237989878999434
2198765456789987678934598798999998767987654989875896543234987898765459987656789876432356793989998945
3249876767897698789523987676789987654598879876976987654345698987654398765446589989873467892198987896
5499987898999539897439876545678996543239998765987898876456789099543239898323478998764578999987656899
6989898969698920976545965434589987654123987654598999876587999198762124987634569659875689998998545678
9878769654567891987689874323698998999934999543459789987679878989653235697545678943989792986987656789
9767656943459932598798765912367899887899898952345678999798767878964376987676799432399891995498979890
7656547892349893459899899899456798776567767893456989999989656767895456798989896999467989894319898921
6543436789598789999921998798987987665425456989569899989876542456796569899696945878979878789498797892
7432125699987678789632987687898998543212345678998789878987431345689878987565434567898765698987676789
6521014569876545699549986546789019654323456989987654567896510234793989876434323457999854897998545678
5432323456987623478998995435678998765434568999876543456789541647892199995321012567998743786965434569
6543436567898786567987894324787899878545679656997632345678932756989098765434143679887632645897323678
7678597998959897879876545213356989987656989545798321236789645669878999876565899798765431236789534989
8789989899643998989985432101245678998767897634987454345679759798767899988776789899986320125678949899
9899878789432129698999543212356899349898998219876595456789898987657899999887896999876541334789659789
3998765698745934567898954326778921239999899101987689879899987654545678999998934598765432345678998698
2129984789659896698987895434567890398798765229998789989989876543234346798789323989876543456789776587
9091095678998789989876989545988921987679954398989892395679997632101245989678919877989654567897665476
8989976789987679876545678959899932399589899987676921234998998743212359876567898766598765878976544345
7678899899876598765434589998789895498998788998545892349877659896323498987478999856449896989865432123
6567778999765459854323498987678796987987676987656789459766545965454987654347896543234987899876521012
5455669897654345965434567898587689876987545698797896598655439876565696543256789654346798923998432123
4323456789543234976545679965476578965498768799898987696543210987896987654345699875457969219876543234
3212567897654345989696789654345467894359879893979998987654521298987898765466797976779854203989876345
4323478998765679998787899743254345943234989912567899898765432349999959899978896797998763212398765456
5544568949878798879899987654101256893123994323456789789876743458901245987899934999899899923989889567
9867679239989987867999876543234347894039876434967897698987856567899349896789129898789987899879987678
1998789198998796656789987654345658965945987845888956567898967678998998765878998789679876789768798989
0129899987987654345895398969656789999896799956789543456799998989997899754567897634598765678956679991
3234999896598987656999219898967998987789898969895432346789989999876798863456789545679654569544589892
9345898765439698767898998787899887786678987898989431235698976798765987654577899966798943569432346789
8976789854323569898967987676798765634569876547878910134567895987654598767678999899987994678921239898
7897899962104456989659876545987654323598765436567891247678934987543459898889998768876889789432378987
6999949873212349876943987432598765212459654323456789349789329876632345999999987654985678996543459876
5878932989923498965732396543459879323598776414567895456895210965321476789989999843534567897666589765
4567891298899987654321987696567989434789887327678976567954341976430127897678998732123457949977678934
3458910987788999876430198987678987645678985438789987878969499876543248976587987653013458932498789123
4567899876577899987921239998789998757989876547899998989998989987854349895476798932154567891349891016
5679998985456999899892345899898999898998987656789899499987878998765456789365698743465898932556954134
8789987656349898789799556799987899979347898768896789329876767999876567893234987654876789543867893246
9898999543298765678678967989896889865456789879965679498765456789987878989125698767987897654978989345
4987998954109854567569879878785678976667899989334567987654357698998989878939789978998998765989879656
3476987893298543203498998765674567897789999892123878996543237577899998969898991989559239876798768967
2345986789987654212567897654523458998999988789294589679632123456999987654767932395432123989899657898
1099875678998854343456789743212347899999876656989694598921012345679876563457893986543014599988746789
2987664569109765467769899852101256789899654345678923987892145659789865432366789997954923459876435789
3996543678919878578879976543213469896798763234567899896789234598999876421245678989899894679895324978
9875432788923987689989989854523498975109892125679998765678949987899965210178789876789789798793212367
9884321467899998789795698767646987894212989096789987654567998756789874321267898765397678987672101256
8765452348998999898654569878959876995429878989899898543479876545878965452358987654234599996543212347
9878767859987987999543478989998765689598767678999769432398766434567896543467895432123489987654423459
7989878967896546798959589499987654878997654567898954321239854323478987854878985432012678998966594878
6594989998965434987898994329876563467987763635967965649398765410789999765989876543124589659878985989
5432397899872129876557889212985432359876432123456896798999876325678959876799998654435678943989876895
4321236789983998983445678909854321234986521012347897987898765434789234989898659765645689659896989934
5210125699899897652136789398765434345995435123456789676799876545892123698987549876756789798765490125
4343234598789789541012893219987545679876876237767898545689998756921012567986532987767899899654321434
5654345987656678932123899329987676789987988946798987434578999867892123456976543498998978998765987645
8776456798734589543474678998798989898998999987899876324567893978954354987987654569999667899976998786
9897767899323479654567899987679999987899567898921965456789912399767765688998765678987556789987899897
1959878998412569767678989876568998976789459999939876567892101989879876789459896789876445898998974998
0245989987323678978789876543479987895678978999899987678954239878989987892365987899873234567899763219
1234599986534589989892987654589876714799989987679298989765398765892198921234598999963125689999654326
4345678987545678999901298767899985423457891298989109999896459876789019210145679987654236789998795435
""";

    static long countLows(String input) {
        Matrix<Integer> heightMap = parse(input);
        Matrix<Boolean> convolution = findLows(heightMap);
        return convolution.countIf(Boolean::booleanValue);
    }

    static long a(String input) {
        Matrix<Integer> heightMap = parse(input);
        Matrix<Boolean> convolution = findLows(heightMap);

        Matrix<Pair<Boolean, Integer>> zip = Matrix.zip(convolution, heightMap);
        Matrix<Integer> masked = zip
                .map(pair -> pair.left() ? pair.right() : 0);

        int sum = Matrix.sum(masked);
        int count = convolution.countIf(Boolean::booleanValue);
        return sum + count;
    }

    private static Matrix<Boolean> findLows(Matrix<Integer> heightMap) {
        BiFunction<Integer, Integer, Boolean> zero = (in, neighbor) -> true;
        Matrix<BiFunction<Integer, Integer, Boolean>> kernel = Matrix.repeat(zero, 3, 3);

        BiFunction<Integer, Integer, Boolean> isLowerThanNeighbor = (center, neighbor) -> nullAsMax(neighbor) > center;
        
        kernel = kernel.with(1, 0, isLowerThanNeighbor); // NORTH
        kernel = kernel.with(1, 2, isLowerThanNeighbor); // SOUTH
        kernel = kernel.with(0, 1, isLowerThanNeighbor); // WEST
        kernel = kernel.with(2, 1, isLowerThanNeighbor); // EAST

        Function<Matrix<Boolean>, Boolean> reducer = window -> window.reduce(list -> list.reduce(Boolean::logicalAnd));

        return heightMap
                .convolution(kernel, Matrix.Convolution.PRESERVE_SIZE, reducer,true, Integer.MAX_VALUE);
    }

    private static Integer nullAsMax(Integer neighbor) {
        return neighbor == null ? Integer.MAX_VALUE : neighbor;
    }

    private static Matrix<Integer> parse(String input) {
        JList<JList<Integer>> listList = JList.ofArray(input.split("\n"))
                .map(A2021_09::parseRow);
        return Matrix.of(listList);
    }

    static JList<Integer> parseRow(String row) {
        return Lists.charactersOf(row)
                .map(String::valueOf)
                .map(Integer::parseUnsignedInt);
    }
}
