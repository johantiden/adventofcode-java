package com.github.johantiden.adventofcode2019;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class D14Test {

    @Test
    public void testParse() {
        String input =
                "10 ORE => 10 A\n" +
                        "1 ORE => 1 B\n" +
                        "7 A, 1 B => 1 C\n" +
                        "7 A, 1 C => 1 D\n" +
                        "7 A, 1 D => 1 E\n" +
                        "7 A, 1 E => 1 FUEL";

        List<D14.Recipe> recipes = D14.Parser.parse(input);

        assertEquals(10l, (long)recipes.get(0).inputs.get("ORE"));
    }

    @Test
    public void testSolveSimple() {
        String input =
                "10 ORE => 10 A\n" +
                        "1 ORE => 1 B\n" +
                        "7 A, 1 B => 1 C\n" +
                        "7 A, 1 C => 1 D\n" +
                        "7 A, 1 D => 1 E\n" +
                        "7 A, 1 E => 1 FUEL";

        List<D14.Recipe> recipes = D14.Parser.parse(input);

        long oreCost = D14.solve(recipes);
        assertEquals(31, oreCost);

    }

    @Test
    public void testSolveSimple2() {
        String input =
                "9 ORE => 2 A\n" +
                        "8 ORE => 3 B\n" +
                        "7 ORE => 5 C\n" +
                        "3 A, 4 B => 1 AB\n" +
                        "5 B, 7 C => 1 BC\n" +
                        "4 C, 1 A => 1 CA\n" +
                        "2 AB, 3 BC, 4 CA => 1 FUEL";

        List<D14.Recipe> recipes = D14.Parser.parse(input);

        long oreCost = D14.solve(recipes);
        assertEquals(165, oreCost);

    }

    @Test
    public void testSolveSimple3() {
        String input =
                "157 ORE => 5 NZVS\n" +
                        "165 ORE => 6 DCFZ\n" +
                        "44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL\n" +
                        "12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ\n" +
                        "179 ORE => 7 PSHF\n" +
                        "177 ORE => 5 HKGWZ\n" +
                        "7 DCFZ, 7 PSHF => 2 XJWVT\n" +
                        "165 ORE => 2 GPVTF\n" +
                        "3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT";

        List<D14.Recipe> recipes = D14.Parser.parse(input);

        long oreCost = D14.solve(recipes);
        assertEquals(13312, oreCost);

    }

    @Test
    public void testSolveSimple4() {
        String input =
                "171 ORE => 8 CNZTR\n" +
                        "7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL\n" +
                        "114 ORE => 4 BHXH\n" +
                        "14 VRPVC => 6 BMBT\n" +
                        "6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL\n" +
                        "6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT\n" +
                        "15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW\n" +
                        "13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW\n" +
                        "5 BMBT => 4 WPTQ\n" +
                        "189 ORE => 9 KTJDG\n" +
                        "1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP\n" +
                        "12 VRPVC, 27 CNZTR => 2 XDBXC\n" +
                        "15 KTJDG, 12 BHXH => 5 XCVML\n" +
                        "3 BHXH, 2 VRPVC => 7 MZWV\n" +
                        "121 ORE => 7 VRPVC\n" +
                        "7 XCVML => 6 RJRHP\n" +
                        "5 BHXH, 4 VRPVC => 5 LTCX";

        List<D14.Recipe> recipes = D14.Parser.parse(input);

        long oreCost = D14.solve(recipes);
        assertEquals(2210736, oreCost);

    }

    @Test
    public void real() {
        String input =
                "9 RJLWC, 9 RJCH => 9 QWFH\n" +
                        "1 XZVHQ, 9 SPQR, 2 WKGVW => 5 KPZB\n" +
                        "12 HPRPM, 4 GTZCK => 7 DJNDX\n" +
                        "7 JKRV, 3 FKTLR, 19 FDSBZ => 9 HPRPM\n" +
                        "9 VTCRJ => 4 SPSW\n" +
                        "2 FDSBZ, 1 FKTLR => 6 KBJF\n" +
                        "9 SPSW => 9 QHVSJ\n" +
                        "5 TFPNF, 11 MNMBX, 1 QCMJ, 13 TXPL, 1 DJNDX, 9 XZVHQ, 2 WKGVW, 2 VQPX => 8 GPKR\n" +
                        "10 DWTC, 8 DSPJG => 4 QCMJ\n" +
                        "100 ORE => 9 XZDP\n" +
                        "3 DBRBD => 4 DKRX\n" +
                        "37 JKRV, 5 FKTLR => 7 VXZN\n" +
                        "3 HWDS, 2 ZRBN => 8 XZVHQ\n" +
                        "15 QNXZV, 53 VXZN, 3 LJQH, 13 FKXVQ, 6 DZGN, 17 MNMBX, 16 GPKR, 8 HWJVK => 1 FUEL\n" +
                        "8 GSLWP => 7 PWTFL\n" +
                        "4 HVPWG => 9 JKRV\n" +
                        "5 NVWGS, 1 QWFH, 9 CWZRS => 2 XPMV\n" +
                        "6 ZRBN => 4 JZDB\n" +
                        "36 BWXWC, 14 HKFD => 3 FMNK\n" +
                        "3 FMNK, 2 SPSW, 16 WKGVW => 6 VQPX\n" +
                        "1 DWTC => 9 VMHM\n" +
                        "3 HPRPM, 1 DWTC => 5 TXPL\n" +
                        "1 KBJF, 2 ZSKSW => 1 MNMBX\n" +
                        "5 JZDB => 4 FDSBZ\n" +
                        "2 FKXVQ => 9 ZTFZG\n" +
                        "17 XZDP => 2 HKFD\n" +
                        "7 VMHM => 3 FGQF\n" +
                        "1 JKRV => 8 CWZRS\n" +
                        "1 WKGVW, 2 SPSW => 6 VLQP\n" +
                        "3 ZRBN => 3 ZSKSW\n" +
                        "7 VXZN, 7 TGLHX => 5 NVWGS\n" +
                        "10 VLQP, 18 FGQF => 4 DBRBD\n" +
                        "8 VMHM => 8 SPQR\n" +
                        "1 KPZB, 4 GQGB, 3 WKGVW => 1 FDSZX\n" +
                        "2 VXZN => 8 VTCRJ\n" +
                        "3 RJLWC => 2 GQGB\n" +
                        "6 TXPL => 4 DSPJG\n" +
                        "2 ZTFZG => 8 TJLW\n" +
                        "1 MPSPS => 3 BWXWC\n" +
                        "5 FMNK, 4 ZSKSW => 5 RWKWD\n" +
                        "137 ORE => 3 MPSPS\n" +
                        "1 VTCRJ, 8 QWFH => 2 GKVQK\n" +
                        "8 RJLWC => 8 TFPNF\n" +
                        "7 TJLW, 1 TFPNF, 16 VQPX, 4 DBRBD, 4 GTZCK, 5 XPMV, 1 FDSZX => 6 DZGN\n" +
                        "1 HVPWG => 7 RJLWC\n" +
                        "18 HVPWG, 9 BWXWC => 4 GSLWP\n" +
                        "107 ORE => 8 RJCH\n" +
                        "1 RJCH => 2 ZRBN\n" +
                        "2 GSLWP, 18 RWKWD, 1 QWFH => 5 LJQH\n" +
                        "3 VXZN, 1 FMNK => 4 TGLHX\n" +
                        "3 HKFD, 6 FMNK => 3 FKTLR\n" +
                        "3 MPSPS => 4 HVPWG\n" +
                        "27 PWTFL, 15 ZTFZG, 6 QHVSJ, 14 DJNDX, 9 RWKWD, 2 MNMBX, 4 DKRX => 6 QNXZV\n" +
                        "1 ZSKSW, 9 KBJF => 3 FKXVQ\n" +
                        "2 FDSBZ => 4 DWTC\n" +
                        "3 HPRPM => 5 HWDS\n" +
                        "1 GKVQK, 1 PWTFL => 5 GTZCK\n" +
                        "1 FGQF => 5 WKGVW\n" +
                        "5 FDSBZ, 7 SPSW => 6 HWJVK";

        List<D14.Recipe> recipes = D14.Parser.parse(input);

        long oreCost = D14.solve(recipes);
        assertEquals(278404, oreCost);

    }


    @Test
    public void testSolve2() {
        String input =
                "157 ORE => 5 NZVS\n" +
                        "165 ORE => 6 DCFZ\n" +
                        "44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL\n" +
                        "12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ\n" +
                        "179 ORE => 7 PSHF\n" +
                        "177 ORE => 5 HKGWZ\n" +
                        "7 DCFZ, 7 PSHF => 2 XJWVT\n" +
                        "165 ORE => 2 GPVTF\n" +
                        "3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT";

        List<D14.Recipe> recipes = D14.Parser.parse(input);

        long fuel = D14.solve2(recipes);
        assertEquals(82892753, fuel);
        }

}
