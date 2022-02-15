package com.github.johantiden.adventofcode._2019;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;

import static com.google.common.primitives.Longs.toArray;

public class A2019_17 {

    private static final long[] INPUT = {1,330,331,332,109,3594,1102,1182,1,15,1102,1,1487,24,1001,0,0,570,1006,570,36,1001,571,0,0,1001,570,-1,570,1001,24,1,24,1106,0,18,1008,571,0,571,1001,15,1,15,1008,15,1487,570,1006,570,14,21102,1,58,0,1106,0,786,1006,332,62,99,21101,0,333,1,21102,73,1,0,1106,0,579,1102,1,0,572,1102,0,1,573,3,574,101,1,573,573,1007,574,65,570,1005,570,151,107,67,574,570,1005,570,151,1001,574,-64,574,1002,574,-1,574,1001,572,1,572,1007,572,11,570,1006,570,165,101,1182,572,127,1001,574,0,0,3,574,101,1,573,573,1008,574,10,570,1005,570,189,1008,574,44,570,1006,570,158,1105,1,81,21101,0,340,1,1106,0,177,21102,1,477,1,1106,0,177,21101,0,514,1,21101,0,176,0,1105,1,579,99,21102,184,1,0,1106,0,579,4,574,104,10,99,1007,573,22,570,1006,570,165,1001,572,0,1182,21102,375,1,1,21102,211,1,0,1106,0,579,21101,1182,11,1,21101,222,0,0,1106,0,979,21101,0,388,1,21102,233,1,0,1105,1,579,21101,1182,22,1,21102,244,1,0,1105,1,979,21102,401,1,1,21102,255,1,0,1106,0,579,21101,1182,33,1,21102,1,266,0,1105,1,979,21102,1,414,1,21101,0,277,0,1106,0,579,3,575,1008,575,89,570,1008,575,121,575,1,575,570,575,3,574,1008,574,10,570,1006,570,291,104,10,21101,1182,0,1,21101,0,313,0,1105,1,622,1005,575,327,1101,1,0,575,21102,327,1,0,1105,1,786,4,438,99,0,1,1,6,77,97,105,110,58,10,33,10,69,120,112,101,99,116,101,100,32,102,117,110,99,116,105,111,110,32,110,97,109,101,32,98,117,116,32,103,111,116,58,32,0,12,70,117,110,99,116,105,111,110,32,65,58,10,12,70,117,110,99,116,105,111,110,32,66,58,10,12,70,117,110,99,116,105,111,110,32,67,58,10,23,67,111,110,116,105,110,117,111,117,115,32,118,105,100,101,111,32,102,101,101,100,63,10,0,37,10,69,120,112,101,99,116,101,100,32,82,44,32,76,44,32,111,114,32,100,105,115,116,97,110,99,101,32,98,117,116,32,103,111,116,58,32,36,10,69,120,112,101,99,116,101,100,32,99,111,109,109,97,32,111,114,32,110,101,119,108,105,110,101,32,98,117,116,32,103,111,116,58,32,43,10,68,101,102,105,110,105,116,105,111,110,115,32,109,97,121,32,98,101,32,97,116,32,109,111,115,116,32,50,48,32,99,104,97,114,97,99,116,101,114,115,33,10,94,62,118,60,0,1,0,-1,-1,0,1,0,0,0,0,0,0,1,12,20,0,109,4,1202,-3,1,587,20101,0,0,-1,22101,1,-3,-3,21102,1,0,-2,2208,-2,-1,570,1005,570,617,2201,-3,-2,609,4,0,21201,-2,1,-2,1106,0,597,109,-4,2106,0,0,109,5,2101,0,-4,630,20101,0,0,-2,22101,1,-4,-4,21102,1,0,-3,2208,-3,-2,570,1005,570,781,2201,-4,-3,653,20102,1,0,-1,1208,-1,-4,570,1005,570,709,1208,-1,-5,570,1005,570,734,1207,-1,0,570,1005,570,759,1206,-1,774,1001,578,562,684,1,0,576,576,1001,578,566,692,1,0,577,577,21101,0,702,0,1106,0,786,21201,-1,-1,-1,1105,1,676,1001,578,1,578,1008,578,4,570,1006,570,724,1001,578,-4,578,21102,1,731,0,1105,1,786,1106,0,774,1001,578,-1,578,1008,578,-1,570,1006,570,749,1001,578,4,578,21101,0,756,0,1106,0,786,1105,1,774,21202,-1,-11,1,22101,1182,1,1,21101,0,774,0,1106,0,622,21201,-3,1,-3,1106,0,640,109,-5,2105,1,0,109,7,1005,575,802,21001,576,0,-6,20102,1,577,-5,1105,1,814,21101,0,0,-1,21101,0,0,-5,21102,1,0,-6,20208,-6,576,-2,208,-5,577,570,22002,570,-2,-2,21202,-5,49,-3,22201,-6,-3,-3,22101,1487,-3,-3,1202,-3,1,843,1005,0,863,21202,-2,42,-4,22101,46,-4,-4,1206,-2,924,21101,1,0,-1,1106,0,924,1205,-2,873,21102,35,1,-4,1105,1,924,1202,-3,1,878,1008,0,1,570,1006,570,916,1001,374,1,374,1202,-3,1,895,1102,1,2,0,1201,-3,0,902,1001,438,0,438,2202,-6,-5,570,1,570,374,570,1,570,438,438,1001,578,558,922,20101,0,0,-4,1006,575,959,204,-4,22101,1,-6,-6,1208,-6,49,570,1006,570,814,104,10,22101,1,-5,-5,1208,-5,43,570,1006,570,810,104,10,1206,-1,974,99,1206,-1,974,1102,1,1,575,21101,973,0,0,1106,0,786,99,109,-7,2106,0,0,109,6,21102,0,1,-4,21101,0,0,-3,203,-2,22101,1,-3,-3,21208,-2,82,-1,1205,-1,1030,21208,-2,76,-1,1205,-1,1037,21207,-2,48,-1,1205,-1,1124,22107,57,-2,-1,1205,-1,1124,21201,-2,-48,-2,1105,1,1041,21102,1,-4,-2,1105,1,1041,21102,1,-5,-2,21201,-4,1,-4,21207,-4,11,-1,1206,-1,1138,2201,-5,-4,1059,1202,-2,1,0,203,-2,22101,1,-3,-3,21207,-2,48,-1,1205,-1,1107,22107,57,-2,-1,1205,-1,1107,21201,-2,-48,-2,2201,-5,-4,1090,20102,10,0,-1,22201,-2,-1,-2,2201,-5,-4,1103,2102,1,-2,0,1105,1,1060,21208,-2,10,-1,1205,-1,1162,21208,-2,44,-1,1206,-1,1131,1105,1,989,21101,439,0,1,1105,1,1150,21101,477,0,1,1105,1,1150,21101,0,514,1,21101,0,1149,0,1105,1,579,99,21102,1,1157,0,1106,0,579,204,-2,104,10,99,21207,-3,22,-1,1206,-1,1138,1202,-5,1,1176,2102,1,-4,0,109,-6,2105,1,0,14,9,40,1,7,1,40,1,7,1,40,1,7,1,40,1,7,1,40,1,7,1,40,1,7,1,40,1,7,1,40,1,3,5,1,7,5,13,14,1,3,1,5,1,5,1,5,1,11,1,14,1,3,1,5,1,5,1,5,1,11,1,14,1,3,1,5,1,5,1,5,1,11,1,14,7,3,1,3,13,7,1,18,1,1,1,3,1,3,1,1,1,5,1,3,1,7,1,18,1,1,1,3,13,3,1,7,1,18,1,1,1,7,1,1,1,9,1,7,1,18,1,1,1,7,1,1,1,9,9,18,1,1,1,7,1,1,1,32,13,1,1,1,1,3,7,22,1,3,1,1,1,5,1,1,1,1,1,3,1,5,1,8,13,1,1,3,13,3,1,5,1,8,1,13,1,5,1,5,1,1,1,5,1,5,1,8,1,13,1,5,1,5,1,1,1,5,1,5,1,8,1,13,1,5,1,5,1,1,1,5,1,5,1,8,1,13,7,5,1,1,13,8,1,25,1,7,1,14,1,5,9,11,1,7,1,14,1,5,1,7,1,11,1,7,1,14,1,5,1,7,1,11,1,7,1,14,1,5,1,7,1,11,1,7,1,14,1,5,1,7,13,7,7,8,1,5,1,33,1,8,7,33,1,48,1,48,1,48,1,48,1,48,1,48,1,48,1,48,1,48,1,40,9,8};
    private static final long[] INPUT_PART2_DONT_TOUCH = new long[]{2, 330, 331, 332, 109, 3594, 1102, 1182, 1, 15, 1102, 1, 1487, 24, 1001, 0, 0, 570, 1006, 570, 36, 1001, 571, 0, 0, 1001, 570, -1, 570, 1001, 24, 1, 24, 1106, 0, 18, 1008, 571, 0, 571, 1001, 15, 1, 15, 1008, 15, 1487, 570, 1006, 570, 14, 21102, 1, 58, 0, 1106, 0, 786, 1006, 332, 62, 99, 21101, 0, 333, 1, 21102, 73, 1, 0, 1106, 0, 579, 1102, 1, 0, 572, 1102, 0, 1, 573, 3, 574, 101, 1, 573, 573, 1007, 574, 65, 570, 1005, 570, 151, 107, 67, 574, 570, 1005, 570, 151, 1001, 574, -64, 574, 1002, 574, -1, 574, 1001, 572, 1, 572, 1007, 572, 11, 570, 1006, 570, 165, 101, 1182, 572, 127, 1001, 574, 0, 0, 3, 574, 101, 1, 573, 573, 1008, 574, 10, 570, 1005, 570, 189, 1008, 574, 44, 570, 1006, 570, 158, 1105, 1, 81, 21101, 0, 340, 1, 1106, 0, 177, 21102, 1, 477, 1, 1106, 0, 177, 21101, 0, 514, 1, 21101, 0, 176, 0, 1105, 1, 579, 99, 21102, 184, 1, 0, 1106, 0, 579, 4, 574, 104, 10, 99, 1007, 573, 22, 570, 1006, 570, 165, 1001, 572, 0, 1182, 21102, 375, 1, 1, 21102, 211, 1, 0, 1106, 0, 579, 21101, 1182, 11, 1, 21101, 222, 0, 0, 1106, 0, 979, 21101, 0, 388, 1, 21102, 233, 1, 0, 1105, 1, 579, 21101, 1182, 22, 1, 21102, 244, 1, 0, 1105, 1, 979, 21102, 401, 1, 1, 21102, 255, 1, 0, 1106, 0, 579, 21101, 1182, 33, 1, 21102, 1, 266, 0, 1105, 1, 979, 21102, 1, 414, 1, 21101, 0, 277, 0, 1106, 0, 579, 3, 575, 1008, 575, 89, 570, 1008, 575, 121, 575, 1, 575, 570, 575, 3, 574, 1008, 574, 10, 570, 1006, 570, 291, 104, 10, 21101, 1182, 0, 1, 21101, 0, 313, 0, 1105, 1, 622, 1005, 575, 327, 1101, 1, 0, 575, 21102, 327, 1, 0, 1105, 1, 786, 4, 438, 99, 0, 1, 1, 6, 77, 97, 105, 110, 58, 10, 33, 10, 69, 120, 112, 101, 99, 116, 101, 100, 32, 102, 117, 110, 99, 116, 105, 111, 110, 32, 110, 97, 109, 101, 32, 98, 117, 116, 32, 103, 111, 116, 58, 32, 0, 12, 70, 117, 110, 99, 116, 105, 111, 110, 32, 65, 58, 10, 12, 70, 117, 110, 99, 116, 105, 111, 110, 32, 66, 58, 10, 12, 70, 117, 110, 99, 116, 105, 111, 110, 32, 67, 58, 10, 23, 67, 111, 110, 116, 105, 110, 117, 111, 117, 115, 32, 118, 105, 100, 101, 111, 32, 102, 101, 101, 100, 63, 10, 0, 37, 10, 69, 120, 112, 101, 99, 116, 101, 100, 32, 82, 44, 32, 76, 44, 32, 111, 114, 32, 100, 105, 115, 116, 97, 110, 99, 101, 32, 98, 117, 116, 32, 103, 111, 116, 58, 32, 36, 10, 69, 120, 112, 101, 99, 116, 101, 100, 32, 99, 111, 109, 109, 97, 32, 111, 114, 32, 110, 101, 119, 108, 105, 110, 101, 32, 98, 117, 116, 32, 103, 111, 116, 58, 32, 43, 10, 68, 101, 102, 105, 110, 105, 116, 105, 111, 110, 115, 32, 109, 97, 121, 32, 98, 101, 32, 97, 116, 32, 109, 111, 115, 116, 32, 50, 48, 32, 99, 104, 97, 114, 97, 99, 116, 101, 114, 115, 33, 10, 94, 62, 118, 60, 0, 1, 0, -1, -1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 12, 20, 0, 109, 4, 1202, -3, 1, 587, 20101, 0, 0, -1, 22101, 1, -3, -3, 21102, 1, 0, -2, 2208, -2, -1, 570, 1005, 570, 617, 2201, -3, -2, 609, 4, 0, 21201, -2, 1, -2, 1106, 0, 597, 109, -4, 2106, 0, 0, 109, 5, 2101, 0, -4, 630, 20101, 0, 0, -2, 22101, 1, -4, -4, 21102, 1, 0, -3, 2208, -3, -2, 570, 1005, 570, 781, 2201, -4, -3, 653, 20102, 1, 0, -1, 1208, -1, -4, 570, 1005, 570, 709, 1208, -1, -5, 570, 1005, 570, 734, 1207, -1, 0, 570, 1005, 570, 759, 1206, -1, 774, 1001, 578, 562, 684, 1, 0, 576, 576, 1001, 578, 566, 692, 1, 0, 577, 577, 21101, 0, 702, 0, 1106, 0, 786, 21201, -1, -1, -1, 1105, 1, 676, 1001, 578, 1, 578, 1008, 578, 4, 570, 1006, 570, 724, 1001, 578, -4, 578, 21102, 1, 731, 0, 1105, 1, 786, 1106, 0, 774, 1001, 578, -1, 578, 1008, 578, -1, 570, 1006, 570, 749, 1001, 578, 4, 578, 21101, 0, 756, 0, 1106, 0, 786, 1105, 1, 774, 21202, -1, -11, 1, 22101, 1182, 1, 1, 21101, 0, 774, 0, 1106, 0, 622, 21201, -3, 1, -3, 1106, 0, 640, 109, -5, 2105, 1, 0, 109, 7, 1005, 575, 802, 21001, 576, 0, -6, 20102, 1, 577, -5, 1105, 1, 814, 21101, 0, 0, -1, 21101, 0, 0, -5, 21102, 1, 0, -6, 20208, -6, 576, -2, 208, -5, 577, 570, 22002, 570, -2, -2, 21202, -5, 49, -3, 22201, -6, -3, -3, 22101, 1487, -3, -3, 1202, -3, 1, 843, 1005, 0, 863, 21202, -2, 42, -4, 22101, 46, -4, -4, 1206, -2, 924, 21101, 1, 0, -1, 1106, 0, 924, 1205, -2, 873, 21102, 35, 1, -4, 1105, 1, 924, 1202, -3, 1, 878, 1008, 0, 1, 570, 1006, 570, 916, 1001, 374, 1, 374, 1202, -3, 1, 895, 1102, 1, 2, 0, 1201, -3, 0, 902, 1001, 438, 0, 438, 2202, -6, -5, 570, 1, 570, 374, 570, 1, 570, 438, 438, 1001, 578, 558, 922, 20101, 0, 0, -4, 1006, 575, 959, 204, -4, 22101, 1, -6, -6, 1208, -6, 49, 570, 1006, 570, 814, 104, 10, 22101, 1, -5, -5, 1208, -5, 43, 570, 1006, 570, 810, 104, 10, 1206, -1, 974, 99, 1206, -1, 974, 1102, 1, 1, 575, 21101, 973, 0, 0, 1106, 0, 786, 99, 109, -7, 2106, 0, 0, 109, 6, 21102, 0, 1, -4, 21101, 0, 0, -3, 203, -2, 22101, 1, -3, -3, 21208, -2, 82, -1, 1205, -1, 1030, 21208, -2, 76, -1, 1205, -1, 1037, 21207, -2, 48, -1, 1205, -1, 1124, 22107, 57, -2, -1, 1205, -1, 1124, 21201, -2, -48, -2, 1105, 1, 1041, 21102, 1, -4, -2, 1105, 1, 1041, 21102, 1, -5, -2, 21201, -4, 1, -4, 21207, -4, 11, -1, 1206, -1, 1138, 2201, -5, -4, 1059, 1202, -2, 1, 0, 203, -2, 22101, 1, -3, -3, 21207, -2, 48, -1, 1205, -1, 1107, 22107, 57, -2, -1, 1205, -1, 1107, 21201, -2, -48, -2, 2201, -5, -4, 1090, 20102, 10, 0, -1, 22201, -2, -1, -2, 2201, -5, -4, 1103, 2102, 1, -2, 0, 1105, 1, 1060, 21208, -2, 10, -1, 1205, -1, 1162, 21208, -2, 44, -1, 1206, -1, 1131, 1105, 1, 989, 21101, 439, 0, 1, 1105, 1, 1150, 21101, 477, 0, 1, 1105, 1, 1150, 21101, 0, 514, 1, 21101, 0, 1149, 0, 1105, 1, 579, 99, 21102, 1, 1157, 0, 1106, 0, 579, 204, -2, 104, 10, 99, 21207, -3, 22, -1, 1206, -1, 1138, 1202, -5, 1, 1176, 2102, 1, -4, 0, 109, -6, 2105, 1, 0, 14, 9, 40, 1, 7, 1, 40, 1, 7, 1, 40, 1, 7, 1, 40, 1, 7, 1, 40, 1, 7, 1, 40, 1, 7, 1, 40, 1, 7, 1, 40, 1, 3, 5, 1, 7, 5, 13, 14, 1, 3, 1, 5, 1, 5, 1, 5, 1, 11, 1, 14, 1, 3, 1, 5, 1, 5, 1, 5, 1, 11, 1, 14, 1, 3, 1, 5, 1, 5, 1, 5, 1, 11, 1, 14, 7, 3, 1, 3, 13, 7, 1, 18, 1, 1, 1, 3, 1, 3, 1, 1, 1, 5, 1, 3, 1, 7, 1, 18, 1, 1, 1, 3, 13, 3, 1, 7, 1, 18, 1, 1, 1, 7, 1, 1, 1, 9, 1, 7, 1, 18, 1, 1, 1, 7, 1, 1, 1, 9, 9, 18, 1, 1, 1, 7, 1, 1, 1, 32, 13, 1, 1, 1, 1, 3, 7, 22, 1, 3, 1, 1, 1, 5, 1, 1, 1, 1, 1, 3, 1, 5, 1, 8, 13, 1, 1, 3, 13, 3, 1, 5, 1, 8, 1, 13, 1, 5, 1, 5, 1, 1, 1, 5, 1, 5, 1, 8, 1, 13, 1, 5, 1, 5, 1, 1, 1, 5, 1, 5, 1, 8, 1, 13, 1, 5, 1, 5, 1, 1, 1, 5, 1, 5, 1, 8, 1, 13, 7, 5, 1, 1, 13, 8, 1, 25, 1, 7, 1, 14, 1, 5, 9, 11, 1, 7, 1, 14, 1, 5, 1, 7, 1, 11, 1, 7, 1, 14, 1, 5, 1, 7, 1, 11, 1, 7, 1, 14, 1, 5, 1, 7, 1, 11, 1, 7, 1, 14, 1, 5, 1, 7, 13, 7, 7, 8, 1, 5, 1, 33, 1, 8, 7, 33, 1, 48, 1, 48, 1, 48, 1, 48, 1, 48, 1, 48, 1, 48, 1, 48, 1, 48, 1, 40, 9, 8};
    private static final ImmutableList<Long> INPUT_PART2 = ImmutableList.copyOf(Arrays.stream(INPUT_PART2_DONT_TOUCH).boxed().collect(Collectors.toList()));

    public static final Pattern ABC = Pattern.compile("[ABC]+");
    public static final int BREAK_LENGTH = 30;

    public static void main(String[] args) throws InterruptedException {
        Pipe output = new Pipe();
        IntcodeComputer ascii = IntcodeComputer.of(INPUT, null, output);

        new Thread(ascii::run).start();
        Map map = parseMap(output);
        System.out.println(map);

        List<Position> intersections = findIntersections(map);
        System.out.println(intersections);

        int hash = hashIntersections(intersections);
        System.out.println(hash);
    }

    static class Part2 {
        private static boolean producerDone = false;

        public static void main(String[] args) throws InterruptedException {
            Pipe output = new Pipe();
            Pipe input = new Pipe();
            IntcodeComputer computer = IntcodeComputer.of(INPUT, input, output);

            Map map = getMap(output, computer);
            BlockingQueue<Map> solutions = new LinkedBlockingQueue<>();
            consumeSolutionsAsync(solutions);

            produceAllSolutions(solutions, map);
        }

        static void produceAllSolutions(BlockingQueue<Map> solutions, Map map) {
            System.out.println(map);

            List<Map> remainingPaths = new LinkedList<>(getHypotheticSteps(map));

            while (!remainingPaths.isEmpty()) {
                map = remainingPaths.remove(0);

                remainingPaths.addAll(getHypotheticSteps(map));

                if (map.isSolved()) {
                    solutions.add(map);
                }
            }

//            System.out.println("Number of solutions produced:" + solutions.size());
//            System.out.println("Done!");
            producerDone = true;
        }

        static void consumeSolutionsAsync(BlockingQueue<Map> solutions) {
            new Thread(() -> {
                try {
                    consumeSolutions(solutions);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }).start();
        }

        private static Map getMap(Pipe output, IntcodeComputer computer) throws InterruptedException {
            Thread thread = new Thread(computer::run);
            thread.start();
            Map map = parseMap(output);
            thread.join();
            return map;
        }

        static void consumeSolutions(BlockingQueue<Map> solutions) throws InterruptedException {
            while (!(solutions.isEmpty() && producerDone)) {
//                System.out.print("!");
                Map map = solutions.take();
                trySolution(map);
            }
            System.out.println("Done consuming solutions.");
        }

        private static void trySolution(Map map) throws InterruptedException {
            ImmutableList<String> instructions = map.instructions;
            instructions = compressForwards(instructions);

//            System.out.println(instructions);

            final String joined = String.join("", instructions);

            for (int aEnd = 2; aEnd < joined.length(); aEnd++) {
                String a = joined.substring(0, aEnd);
                if (a.length() > BREAK_LENGTH) {
                    break;
                }

                String afterA = joined.replaceAll(a, "A");

                int bStart = -1;
                for (int i = 0; i < afterA.length(); i++) {
                    if (afterA.charAt(i) == 'A') {
                        continue;
                    } else
                        bStart = i;
                    break;
                }

                if (afterA.charAt(bStart) == 'A') {
                    continue;
                }
                for (int bEnd = bStart + 2; bEnd < afterA.length(); bEnd++) {
                    String b = afterA.substring(bStart, bEnd);
                    if (b.length() > BREAK_LENGTH) {
                        break;
                    }
                    if (b.contains("A")) {
                        break;
                    }

                    String afterB = afterA.replaceAll(b, "B");

                    int cStart = -1;
                    for (int i = 0; i < afterB.length(); i++) {
                        if (afterB.charAt(i) == 'A' || afterB.charAt(i) == 'B') {
                            continue;
                        } else
                            cStart = i;
                        break;
                    }

                    if (afterB.charAt(cStart) == 'A' || afterB.charAt(cStart) == 'B') {
                        continue;
                    }

                    for (int cEnd = cStart + 1; cEnd < afterB.length()+1; cEnd++) {
                        String c = afterB.substring(cStart, cEnd);

                        if (c.contains("A") || c.contains("B")) {
                            break;
                        }

                        if (c.length() > BREAK_LENGTH) {
                            break;
                        }

                        String afterC = afterB.replaceAll(c, "C");

                        if (afterC.length() >= BREAK_LENGTH) {
                            continue;
                        }
//                                System.out.println(">>>>> " + afterC);

                        boolean matches = ABC.matcher(afterC).matches();
                        if (!matches) {
                            continue;
                        }

                        System.out.println();
                        System.out.println("OMG");
                        System.out.println("routine: " + afterC);
                        System.out.println("A:" + a);
                        System.out.println("B:" + b);
                        System.out.println("C:" + c);

                        tryToWin(afterC, a, b, c);
                    }
                }
            }


            boolean a = true;
        }

        private static void tryToWin(String abc, String a, String b, String c) throws InterruptedException {
            Pipe input = new Pipe();
            Pipe output = new Pipe();
            IntcodeComputer computer = IntcodeComputer.of(toArray(INPUT_PART2), input, output);

            Thread thread = new Thread(computer::run);
            thread.start();

            Map map = parseMap(output); // ignore map

            abc = addCommasAfterEveryChar(abc);
            a = addCommas(a);
            b = addCommas(b);
            c = addCommas(c);

            if (abc.length() > 20 || a.length() > 20 || b.length() > 20 || c.length() > 20) {
                System.out.println(" too long");
                return;
            }

            send(abc, input);
            send(a, input);
            send(b, input);
            send(c, input);
            send("n", input);


            readLine(output);
            readLine(output);
            readLine(output);
            readLine(output);
            readLine(output);
            readLine(output);//empty line



            parseMap(output); // ignore map

            while (output.hasNext()) {
                long read1 = output.read();
                char read = (char) read1;
                System.out.print(read);
                System.out.println(read1);
            }

//            while (true) {
//                Map map = getMap(output, computer);
//                System.out.println(map);
//                if (map.isSolved()) {
//
//                    System.out.println("response: " + output.read());
//
//                    thread.interrupt();
//                    thread.join();
//                    break;
//                }
//            }

        }

        private static void readLine(Pipe output) throws InterruptedException {
            long l = 0;
            while (l != 10) {
                l = output.read();
                char read = (char) l;
                System.out.print(read);
            }
        }

        static String addCommas(String s) {
            String replaceAll = s.replaceAll("([LR]|[0-9]+)", "$1,");
            return replaceAll
                    .substring(0, replaceAll.length()-1);
        }

        private static void send(String buffer, Pipe input) {
            if (buffer.length() > 20) {
                throw new IllegalArgumentException("Too large buffer!");
            }

            for (int i = 0; i < buffer.length(); i++) {
                char c = buffer.charAt(i);
                int integer = c;
                input.write(integer);
            }
            input.write(10);
        }

        private static String addCommasAfterEveryChar(String source) {
            String s = "";
            for (int i = 0; i < source.length(); i++) {
                s += source.charAt(i) + ",";
            }
            return s
                    .substring(0, s.length()-1);
        }

        static ImmutableList<String> compressForwards(ImmutableList<String> instructions) {
            for (int i = 0; i < instructions.size() - 1; i++) {
                if (isNumber(instructions.get(i)) && isNumber(instructions.get(i+1))) {
                    int a = Integer.parseInt(instructions.get(i));
                    int b = Integer.parseInt(instructions.get(i+1));
                    int sum = a+b;

                    ImmutableList.Builder<String> builder = ImmutableList.builder();
                    builder.addAll(instructions.subList(0, i));
                    builder.add(""+sum);
                    builder.addAll(instructions.subList(i+2, instructions.size()));
                    return compressForwards(builder.build());
                }
            }

            return instructions;
        }

        private static boolean isNumber(String s) {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    }

    static List<Position> findIntersections(Map map) {
        List<Position> intersections = new ArrayList<>();

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Position position = new Position(x, y);

                boolean isPlus = convolutionKernelPlus(position).stream().allMatch(p -> map.get(p) == Tile.SCAFFOLD);
                if (isPlus) {
                    intersections.add(position);
                }
            }
        }
        return intersections;
    }

    private static List<Position> convolutionKernelPlus(Position center) {
        List<Position> list = new ArrayList<>();

        list.add(center);

        Direction.theValues().stream().map(d -> d.step(center)).forEach(list::add);

        return list;
    }

    private static Map parseMap(IntcodeComputer.Input input) throws InterruptedException {
        char c = getChar(input);
        char c1 = c;
        String s = ""+c;

        while (!(c == 10 && c1 == 10)) {
            c1 = c;
            c = getChar(input);
            s += c;
        }

        return Map.parse(s);
    }

    private static char getChar(IntcodeComputer.Input input) throws InterruptedException {
        long l = input.read();
        return (char) l;
    }

    public static int hashIntersections(List<Position> intersections) {
        return intersections.stream()
                .mapToInt(p -> p.x * p.y)
                .sum();
    }

    static class Map {
        private final ImmutableList<String> instructions;
        private final ImmutableMap<Position, Tile> map;
        private final int height;
        private final int width;

        Map(ImmutableList<String> instructions, ImmutableMap<Position, Tile> map, int height, int width) {
            this.instructions = instructions;
            this.map = map;
            this.height = height;
            this.width = width;
        }

        Map with(ImmutableList<String> robotInstructionsFromParent, Walker moved) {
            HashMap<Position, Tile> copy = new HashMap<>(map);

            copy.put(findRobot().position, Tile.VISITED);
            copy.put(moved.position, toTile(moved.direction));

            return new Map(
                    join(instructions, robotInstructionsFromParent),
                    ImmutableMap.copyOf(copy), height, width);
        }

        private <T> ImmutableList<T> join(ImmutableList<T> a, ImmutableList<T> b) {
            ImmutableList.Builder<T> builder = ImmutableList.builder();
            builder.addAll(a);
            builder.addAll(b);
            return builder.build();
        }

        static Map parse(String s) {
            String[] rows = s.split("\n");
            ImmutableMap.Builder<Position, Tile> map1 = ImmutableMap.builder();

            int height1 = rows.length;
            int width1 = rows[0].length();

            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    Tile tile = Tile.of(rows[y].charAt(x));
                    if (tile != Tile.NONE) { // sparse yay!
                        map1.put(new Position(x, y), tile);
                    }
                }
            }

            return new Map(ImmutableList.of(), map1.build(), height1, width1);
        }

        @Nonnull
        private Tile get(Position p) {
            return map.getOrDefault(p, Tile.NONE);
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }


        @Override
        public String toString() {
            String s = "";

            s += instructions + "\n";

            for (int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    s += get(new Position(x, y));
                }
                s += "\n";
            }



            return s;
        }

        public Walker findRobot() {
            return map.entrySet().stream()
                    .filter(e -> isRobotTile(e.getValue()))
                    .findAny()
                    .map(e -> new Walker(e.getKey(), toDirection(e.getValue())))
                    .orElseThrow(() -> new IllegalStateException("No robot found!"));
        }

        private static boolean isRobotTile(Tile tile) {
            switch (tile) {
                case EAST:
                case WEST:
                case NORTH:
                case SOUTH:
                    return true;
                default:
                    return false;
            }
        }

        private static Direction toDirection(Tile tile) {
            switch (tile) {
                case SOUTH: return Direction.SOUTH;
                case NORTH: return Direction.NORTH;
                case WEST: return Direction.WEST;
                case EAST: return Direction.EAST;
                default:
                    throw new IllegalArgumentException(tile + " cannot be converted into Direction.");
            }
        }

        private static Tile toTile(Direction direction) {
            switch (direction) {
                case SOUTH: return Tile.SOUTH;
                case NORTH: return Tile.NORTH;
                case WEST: return Tile.WEST;
                case EAST: return Tile.EAST;
                default:
                    throw new IllegalArgumentException(direction + " cannot be converted into Tile.");
            }
        }

        public boolean isWorthWalkingTo(Position position) {
            // We may need to revisit nodes if they are intersections and any of their neighbors is interesting.
            return get(position) != Tile.VISITED ||
                    getNeighborScaffolds(position).stream().anyMatch(p -> get(p) == Tile.SCAFFOLD);
        }

        List<Position> getNeighborScaffolds(Position position) {
            return Direction.theValues().stream()
                    .map(d -> d.step(position))
                    .filter(p -> {
                        Tile tile = get(p);
                        return tile == Tile.SCAFFOLD || tile == Tile.VISITED;
                    })
                    .collect(Collectors.toList());
        }

        public boolean isSolved() {
            return map.values().stream()
                    .noneMatch(t -> t == Tile.SCAFFOLD);
        }
    }


    static List<Map> getHypotheticSteps(Map map) {
        Walker robot = map.findRobot();

        if (map.get(robot.step().position) != Tile.NONE) {
            // if we can walk straight. Always do that.
            Map next = map.with(ImmutableList.of("1"), map.findRobot().walk(1));
            return Lists.newArrayList(next);
        }


        List<Position> possibleLocations = map.getNeighborScaffolds(robot.position).stream()
                .filter(map::isWorthWalkingTo)
                .collect(Collectors.toList());

        List<Map> children = new ArrayList<>();

        for (Position target : possibleLocations) {
            if (robot.step().position.equals(target)) {
                Map next = map.with(ImmutableList.of("1"), map.findRobot().walk(1));
                children.add(next);
            } else if (robot.flip().step().position.equals(target)) {
                // dont backtrack
            } else if (robot.direction.right().step(robot.position).equals(target)) {
                Map next = map.with(ImmutableList.of("R", "1"), map.findRobot().left().left().left().walk(1));
                children.add(next);
            } else if (robot.direction.left().step(robot.position).equals(target)) {
                Map next = map.with(ImmutableList.of("L", "1"), map.findRobot().left().walk(1));
                children.add(next);
            }
        }

        return children;
    }

    enum Tile {
        NONE("."),
        SCAFFOLD("#"),
        VISITED("¤"),
        NORTH("^"),
        EAST(">"),
        SOUTH("v"),
        WEST("<"),
        ;

        private final String c;

        Tile(String c) {
            this.c = c;
        }

        public static Tile of(char c) {
            return Arrays.stream(values())
                    .filter(t -> t.c.charAt(0) == c)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Could not parse '"+c+"'"));
        }

        @Override
        public String toString() {
            return c;
        }
    }
}


