package com.github.johantiden.adventofcode._2021;

import com.github.johantiden.adventofcode.common.graph.Edge;
import com.github.johantiden.adventofcode.common.graph.Graph;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class A2021_12Test {


    public static final String FIRST_EXAMPLE =
"""
start-A
start-b
A-c
A-b
b-d
A-end
b-end
""";

    public static final String SLIGHTLY_LARGER_EXAMPLE =
"""
dc-end
HN-start
start-kj
dc-start
dc-HN
LN-dc
HN-end
kj-sa
kj-HN
kj-dc
""";


    public static final String EVEN_LARGER_EXAMPLE =
"""
fs-end
he-DX
fs-he
start-DX
pj-DX
end-zg
zg-sl
zg-pj
pj-he
RW-he
fs-DX
pj-RW
zg-RW
start-pj
he-WI
zg-he
pj-fs
start-RW
""";

    public static final String REAL =
"""
vn-DD
qm-DD
MV-xy
end-xy
KG-end
end-kw
qm-xy
start-vn
MV-vn
vn-ko
lj-KG
DD-xy
lj-kh
lj-MV
ko-MV
kw-qm
qm-MV
lj-kw
VH-lj
ko-qm
ko-start
MV-start
DD-ko

""";


    @Test
    public void a() {
        assertThat(A2021_12.a(FIRST_EXAMPLE), is(10L));
        assertThat(A2021_12.a(SLIGHTLY_LARGER_EXAMPLE), is(19L));
        assertThat(A2021_12.a(EVEN_LARGER_EXAMPLE), is(226L));

        assertThat(A2021_12.a(REAL), is(3292L));
    }

    @Test
    public void b() {
        assertThat(A2021_12.b(FIRST_EXAMPLE), is(36L));
        assertThat(A2021_12.b(SLIGHTLY_LARGER_EXAMPLE), is(103L));
        assertThat(A2021_12.b(EVEN_LARGER_EXAMPLE), is(3509L));

        assertThat(A2021_12.b(REAL), is(-1L));
    }


    @Test
    public void testParse() {
        Graph graph = A2021_12.parse(FIRST_EXAMPLE);
        assertThat(graph.edges().get(0), is(new Edge("start", "A")));
    }
}
