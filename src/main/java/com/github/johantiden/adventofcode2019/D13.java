package com.github.johantiden.adventofcode2019;

import com.google.common.util.concurrent.RateLimiter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class D13 {


    private static final long[] PROGRAM = {1, 380, 379, 385, 1008, 2709, 828459, 381, 1005, 381, 12, 99, 109, 2710, 1102, 1, 0, 383, 1102, 1, 0, 382, 20101, 0, 382, 1, 21002, 383, 1, 2, 21102, 1, 37, 0, 1106, 0, 578, 4, 382, 4, 383, 204, 1, 1001, 382, 1, 382, 1007, 382, 45, 381, 1005, 381, 22, 1001, 383, 1, 383, 1007, 383, 23, 381, 1005, 381, 18, 1006, 385, 69, 99, 104, -1, 104, 0, 4, 386, 3, 384, 1007, 384, 0, 381, 1005, 381, 94, 107, 0, 384, 381, 1005, 381, 108, 1106, 0, 161, 107, 1, 392, 381, 1006, 381, 161, 1102, 1, -1, 384, 1106, 0, 119, 1007, 392, 43, 381, 1006, 381, 161, 1101, 0, 1, 384, 20102, 1, 392, 1, 21102, 1, 21, 2, 21101, 0, 0, 3, 21102, 138, 1, 0, 1106, 0, 549, 1, 392, 384, 392, 20101, 0, 392, 1, 21102, 1, 21, 2, 21102, 3, 1, 3, 21102, 1, 161, 0, 1105, 1, 549, 1102, 1, 0, 384, 20001, 388, 390, 1, 21002, 389, 1, 2, 21102, 180, 1, 0, 1106, 0, 578, 1206, 1, 213, 1208, 1, 2, 381, 1006, 381, 205, 20001, 388, 390, 1, 21001, 389, 0, 2, 21101, 205, 0, 0, 1105, 1, 393, 1002, 390, -1, 390, 1101, 0, 1, 384, 20101, 0, 388, 1, 20001, 389, 391, 2, 21101, 228, 0, 0, 1106, 0, 578, 1206, 1, 261, 1208, 1, 2, 381, 1006, 381, 253, 21001, 388, 0, 1, 20001, 389, 391, 2, 21101, 253, 0, 0, 1105, 1, 393, 1002, 391, -1, 391, 1102, 1, 1, 384, 1005, 384, 161, 20001, 388, 390, 1, 20001, 389, 391, 2, 21101, 0, 279, 0, 1105, 1, 578, 1206, 1, 316, 1208, 1, 2, 381, 1006, 381, 304, 20001, 388, 390, 1, 20001, 389, 391, 2, 21101, 0, 304, 0, 1105, 1, 393, 1002, 390, -1, 390, 1002, 391, -1, 391, 1102, 1, 1, 384, 1005, 384, 161, 21002, 388, 1, 1, 21001, 389, 0, 2, 21101, 0, 0, 3, 21101, 0, 338, 0, 1105, 1, 549, 1, 388, 390, 388, 1, 389, 391, 389, 20102, 1, 388, 1, 20102, 1, 389, 2, 21101, 4, 0, 3, 21101, 365, 0, 0, 1105, 1, 549, 1007, 389, 22, 381, 1005, 381, 75, 104, -1, 104, 0, 104, 0, 99, 0, 1, 0, 0, 0, 0, 0, 0, 329, 20, 18, 1, 1, 22, 109, 3, 21202, -2, 1, 1, 21202, -1, 1, 2, 21101, 0, 0, 3, 21102, 414, 1, 0, 1105, 1, 549, 21202, -2, 1, 1, 22101, 0, -1, 2, 21101, 429, 0, 0, 1105, 1, 601, 1202, 1, 1, 435, 1, 386, 0, 386, 104, -1, 104, 0, 4, 386, 1001, 387, -1, 387, 1005, 387, 451, 99, 109, -3, 2105, 1, 0, 109, 8, 22202, -7, -6, -3, 22201, -3, -5, -3, 21202, -4, 64, -2, 2207, -3, -2, 381, 1005, 381, 492, 21202, -2, -1, -1, 22201, -3, -1, -3, 2207, -3, -2, 381, 1006, 381, 481, 21202, -4, 8, -2, 2207, -3, -2, 381, 1005, 381, 518, 21202, -2, -1, -1, 22201, -3, -1, -3, 2207, -3, -2, 381, 1006, 381, 507, 2207, -3, -4, 381, 1005, 381, 540, 21202, -4, -1, -1, 22201, -3, -1, -3, 2207, -3, -4, 381, 1006, 381, 529, 22102, 1, -3, -7, 109, -8, 2105, 1, 0, 109, 4, 1202, -2, 45, 566, 201, -3, 566, 566, 101, 639, 566, 566, 2102, 1, -1, 0, 204, -3, 204, -2, 204, -1, 109, -4, 2106, 0, 0, 109, 3, 1202, -1, 45, 594, 201, -2, 594, 594, 101, 639, 594, 594, 20102, 1, 0, -2, 109, -3, 2105, 1, 0, 109, 3, 22102, 23, -2, 1, 22201, 1, -1, 1, 21102, 1, 521, 2, 21102, 1, 730, 3, 21102, 1, 1035, 4, 21101, 0, 630, 0, 1105, 1, 456, 21201, 1, 1674, -2, 109, -3, 2106, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 0, 2, 0, 0, 2, 2, 0, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 0, 0, 2, 2, 2, 0, 0, 1, 1, 0, 0, 0, 2, 0, 2, 2, 0, 0, 0, 2, 2, 0, 2, 2, 0, 0, 2, 0, 0, 0, 2, 0, 2, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 1, 1, 0, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 2, 0, 2, 2, 0, 0, 2, 0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 0, 2, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, 0, 2, 0, 1, 1, 0, 2, 0, 2, 0, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 2, 0, 0, 2, 0, 0, 2, 0, 1, 1, 0, 0, 2, 0, 2, 2, 2, 0, 0, 0, 2, 2, 0, 0, 0, 2, 2, 0, 0, 0, 2, 0, 0, 2, 2, 0, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 0, 2, 0, 0, 2, 0, 0, 1, 1, 0, 2, 2, 0, 2, 2, 0, 2, 2, 0, 0, 0, 2, 2, 2, 0, 2, 0, 0, 2, 2, 0, 2, 0, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 2, 2, 0, 0, 0, 2, 2, 0, 0, 1, 1, 0, 2, 2, 0, 2, 0, 0, 2, 2, 0, 0, 2, 0, 0, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 2, 0, 0, 2, 2, 2, 0, 1, 1, 0, 2, 2, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 2, 2, 0, 0, 2, 2, 0, 0, 0, 0, 0, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 0, 2, 0, 2, 2, 0, 0, 0, 0, 1, 1, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 2, 0, 2, 2, 2, 2, 2, 0, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 2, 0, 0, 1, 1, 0, 2, 2, 0, 0, 0, 2, 2, 0, 2, 2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 2, 2, 0, 2, 2, 2, 2, 0, 1, 1, 0, 2, 0, 0, 2, 2, 2, 0, 0, 2, 0, 2, 0, 2, 0, 2, 2, 0, 2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 2, 0, 2, 2, 2, 2, 0, 0, 1, 1, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 2, 2, 0, 2, 0, 2, 0, 0, 2, 2, 0, 2, 2, 2, 0, 2, 2, 0, 0, 2, 0, 2, 0, 2, 0, 0, 1, 1, 0, 2, 0, 2, 2, 0, 2, 2, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 2, 0, 2, 0, 2, 0, 0, 2, 2, 0, 2, 0, 2, 0, 2, 2, 2, 0, 0, 2, 0, 2, 2, 2, 0, 1, 1, 0, 2, 0, 2, 2, 0, 2, 2, 2, 0, 0, 2, 0, 0, 2, 2, 2, 2, 2, 0, 0, 2, 2, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 0, 2, 0, 2, 2, 2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 2, 2, 0, 2, 2, 0, 2, 0, 2, 2, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 19, 57, 3, 10, 63, 4, 73, 55, 28, 50, 19, 46, 18, 78, 18, 9, 25, 36, 40, 29, 86, 84, 2, 46, 1, 16, 66, 12, 94, 19, 6, 76, 86, 17, 18, 59, 9, 53, 19, 74, 29, 61, 89, 19, 50, 4, 10, 57, 89, 29, 42, 27, 11, 29, 9, 76, 86, 3, 53, 87, 20, 16, 58, 5, 11, 53, 93, 72, 15, 84, 7, 49, 91, 43, 44, 75, 37, 91, 73, 10, 86, 46, 97, 7, 79, 5, 55, 40, 10, 93, 57, 50, 32, 6, 82, 4, 35, 25, 86, 81, 18, 63, 26, 70, 47, 87, 44, 55, 74, 1, 92, 18, 87, 15, 93, 75, 31, 51, 70, 48, 9, 54, 86, 41, 39, 76, 76, 69, 27, 25, 24, 82, 26, 77, 3, 46, 90, 81, 53, 92, 10, 87, 82, 22, 72, 60, 57, 58, 20, 20, 68, 50, 36, 49, 62, 45, 98, 65, 25, 51, 71, 43, 63, 50, 48, 85, 70, 37, 27, 60, 75, 8, 48, 73, 68, 52, 1, 66, 58, 49, 40, 50, 10, 34, 40, 29, 75, 57, 21, 55, 43, 19, 21, 49, 32, 77, 87, 50, 23, 58, 25, 66, 46, 37, 60, 59, 15, 40, 80, 23, 32, 89, 96, 80, 31, 48, 73, 48, 69, 42, 71, 47, 60, 52, 93, 67, 84, 2, 70, 9, 75, 53, 78, 59, 43, 21, 17, 32, 24, 10, 38, 39, 76, 2, 54, 78, 18, 42, 64, 63, 3, 75, 3, 55, 34, 92, 48, 89, 65, 61, 20, 94, 64, 48, 85, 93, 50, 62, 10, 31, 48, 29, 43, 89, 52, 82, 42, 36, 1, 92, 12, 78, 53, 88, 22, 93, 86, 62, 53, 46, 69, 2, 7, 71, 13, 18, 29, 9, 9, 8, 19, 49, 79, 32, 9, 59, 32, 84, 6, 84, 7, 27, 89, 7, 84, 56, 62, 5, 75, 33, 64, 19, 97, 23, 77, 13, 14, 19, 36, 93, 10, 36, 20, 25, 2, 98, 20, 49, 25, 97, 70, 32, 7, 24, 41, 49, 26, 61, 42, 47, 3, 50, 13, 69, 87, 73, 85, 39, 85, 82, 91, 95, 37, 53, 63, 47, 25, 4, 66, 43, 41, 7, 43, 48, 7, 9, 89, 64, 67, 6, 38, 76, 84, 48, 22, 31, 23, 32, 28, 76, 1, 21, 49, 88, 47, 75, 79, 45, 75, 21, 93, 38, 25, 53, 86, 13, 22, 64, 35, 77, 98, 48, 44, 88, 65, 37, 93, 30, 86, 93, 65, 67, 86, 69, 87, 2, 16, 83, 21, 44, 12, 2, 42, 90, 95, 81, 58, 76, 8, 61, 42, 77, 58, 57, 12, 18, 3, 14, 43, 97, 81, 57, 16, 80, 65, 42, 43, 16, 71, 46, 31, 40, 31, 91, 12, 5, 10, 97, 79, 54, 35, 51, 80, 1, 4, 39, 47, 30, 67, 96, 10, 34, 51, 90, 81, 17, 2, 45, 8, 64, 58, 12, 82, 45, 18, 69, 22, 14, 38, 94, 28, 97, 5, 47, 53, 51, 67, 38, 59, 20, 61, 49, 65, 83, 31, 62, 23, 83, 48, 41, 61, 75, 18, 67, 37, 82, 15, 74, 43, 88, 25, 98, 73, 28, 88, 53, 8, 6, 41, 94, 8, 65, 34, 12, 40, 1, 23, 46, 36, 83, 96, 44, 70, 71, 39, 59, 82, 83, 51, 53, 9, 25, 84, 68, 3, 78, 2, 16, 98, 6, 46, 54, 68, 97, 83, 88, 92, 7, 82, 73, 59, 68, 24, 93, 19, 19, 37, 87, 1, 42, 50, 41, 12, 88, 43, 16, 1, 88, 50, 84, 13, 80, 28, 4, 47, 62, 72, 98, 97, 73, 45, 67, 53, 6, 65, 90, 31, 71, 94, 15, 21, 20, 81, 23, 57, 62, 75, 16, 19, 4, 43, 63, 41, 98, 9, 50, 23, 11, 78, 11, 70, 22, 82, 97, 70, 58, 94, 77, 77, 71, 21, 86, 57, 67, 76, 33, 3, 43, 39, 37, 88, 68, 14, 91, 95, 7, 46, 42, 35, 44, 52, 61, 52, 91, 47, 38, 50, 94, 92, 32, 33, 59, 18, 15, 30, 10, 85, 90, 39, 6, 2, 40, 66, 51, 16, 47, 56, 95, 98, 49, 60, 69, 53, 61, 55, 59, 11, 98, 75, 78, 45, 18, 96, 82, 85, 58, 57, 49, 2, 48, 34, 30, 50, 63, 14, 52, 65, 85, 50, 53, 31, 74, 17, 98, 57, 74, 77, 85, 68, 92, 53, 54, 48, 42, 35, 52, 17, 93, 43, 9, 5, 90, 33, 16, 60, 72, 62, 66, 81, 40, 91, 14, 83, 4, 96, 50, 4, 56, 12, 14, 93, 95, 38, 38, 49, 94, 18, 22, 69, 44, 71, 18, 88, 38, 20, 55, 78, 53, 83, 3, 73, 25, 2, 64, 24, 63, 25, 18, 47, 2, 97, 88, 64, 29, 58, 93, 35, 36, 61, 64, 6, 5, 52, 75, 18, 2, 89, 68, 38, 22, 42, 19, 34, 32, 58, 81, 60, 98, 8, 67, 5, 51, 59, 98, 88, 70, 46, 33, 82, 88, 27, 41, 85, 44, 40, 90, 4, 41, 36, 32, 5, 91, 20, 4, 41, 62, 16, 16, 36, 50, 92, 93, 94, 88, 16, 30, 94, 70, 8, 13, 26, 35, 80, 98, 34, 6, 98, 92, 85, 1, 9, 31, 40, 10, 38, 87, 61, 11, 89, 69, 26, 1, 11, 29, 71, 6, 34, 11, 95, 45, 14, 42, 44, 9, 40, 22, 95, 75, 17, 18, 13, 95, 60, 93, 74, 53, 93, 71, 54, 68, 8, 51, 16, 18, 53, 62, 87, 32, 18, 42, 74, 38, 54, 82, 89, 61, 28, 47, 29, 68, 83, 63, 65, 11, 1, 4, 42, 85, 61, 70, 90, 12, 80, 84, 5, 58, 7, 88, 70, 93, 64, 92, 4, 21, 80, 80, 92, 63, 53, 47, 11, 38, 91, 80, 38, 13, 81, 23, 25, 16, 66, 96, 50, 37, 73, 80, 87, 50, 84, 48, 27, 69, 86, 52, 86, 36, 98, 21, 92, 94, 91, 30, 68, 56, 8, 45, 31, 6, 57, 6, 51, 88, 77, 77, 50, 21, 81, 22, 79, 95, 60, 42, 54, 65, 65, 97, 15, 15, 91, 32, 59, 25, 46, 93, 25, 828459};

    public static void main(String[] args) {
        Screen screen = new Screen();
        VideoDriver video = new VideoDriver(screen, null);

        IntcodeComputer computer = IntcodeComputer.of(
                PROGRAM,
                null,
                video
        );

        computer.run();


        long count = screen.map.values().stream()
                .filter(tileId -> tileId == TileId.BLOCK)
                .count();

        System.out.println(count);

    }


    static class PartTwo {
        public static void main(String[] args) {
            Screen screen = new Screen();
            VideoDriver video = new VideoDriver(screen, score -> System.out.println("Score: " + score));

            PROGRAM[0] = 2;

            JFrame jFrame = new JFrame() {
                @Override
                public void paint(Graphics g) {
                    int tileSize = 10;

                    Screen copy = screen.copy();
                    for (int y = 0; y < copy.maxY(); y++) {
                        for (int x = 0; x < copy.maxX(); x++) {
                            g.setColor(copy.get(new Position(x, y)).getColor());
                            g.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
                        }
                    }

                }
            };

            jFrame.setBounds(0, 0, 1000, 1000);
            jFrame.setVisible(true);

            HumanJoystick humanJoystick = new HumanJoystick();
            AIJoystick aiJoystick = new AIJoystick(screen);

            jFrame.addKeyListener(humanJoystick);
            jFrame.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    jFrame.invalidate();
                }
            });

            screen.setOnInvalidated(() -> {
//                System.out.println("Screen!");
                SwingUtilities.invokeLater(jFrame::repaint);
            });


            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            while (true) {
                IntcodeComputer computer = IntcodeComputer.of(
                        PROGRAM,
                        aiJoystick,
                        video
                );

                computer.run();
                System.out.println("Game over!");
            }
        }
    }



    static class AIJoystick implements IntcodeComputer.Input {

        final Screen screen;
        RateLimiter rateLimiter = RateLimiter.create(100);

        AIJoystick(Screen screen) {
            this.screen = screen;
        }

        @Override
        public long read() {
            rateLimiter.acquire();

            Position paddle = screen.findPaddle().get(0);
            Position ball = screen.findBall().get();

            int dx = ball.x - paddle.x;

            return (long) Math.signum(dx);
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException();
        }
    }


    static class HumanJoystick extends KeyAdapter implements IntcodeComputer.Input {

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        @Override
        public long read() throws InterruptedException {
            return queue.take();
        }

        @Override
        public boolean hasNext() {
            return queue.peek() != null;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (queue.isEmpty()) {
                if (e.getKeyChar() == 'a') {
                    queue.offer(-1);
                }
                if (e.getKeyChar() == 's') {
                    queue.offer(0);
                }
                if (e.getKeyChar() == 'd') {
                    queue.offer(1);
                }
            }
        }
    }

    static class VideoDriver implements IntcodeComputer.Output {

        final Screen screen;
        final IntcodeComputer.Output scoreOutput;
        Long x;
        Long y;

        VideoDriver(Screen screen, IntcodeComputer.Output scoreOutput) {
            this.screen = screen;
            this.scoreOutput = scoreOutput;
        }

        @Override
        public void write(long output) {
            if (x == null) {
                x = output;
            } else if (y == null) {
                y = output;
            } else {
                if (x == -1 && y == 0) {
                    onScoreUpdated(output);
                    clearState();
                } else {
//                    System.out.println("VideoDriver time to draw!");
                    TileId tileId = TileId.values()[(int) output];
                    screen.put(new Position(x.intValue(), y.intValue()), tileId);
                    clearState();
                }
            }
        }

        private void onScoreUpdated(long output) {
            scoreOutput.write(output);
        }

        private void clearState() {
            x = null;
            y = null;
        }
    }

    enum TileId {
        EMPTY,
        WALL,
        BLOCK,
        HORIZONTAL_PADDLE,
        BALL;

        public Color getColor() {
            switch (this) {
                case EMPTY: return Color.BLACK;
                case BALL: return Color.RED;
                case WALL: return Color.WHITE;
                case BLOCK:return Color.CYAN;
                case HORIZONTAL_PADDLE: return Color.BLUE;
                default:throw new RuntimeException("Unsupported TileId");
            }
        }
    }

    static class Screen {
        Map<Position, TileId> map = new HashMap<>();
        private Runnable invalidatedCallback;

        synchronized void put(int x, int y, TileId tileId) {
            Position position = new Position(x, y);
            put(position, tileId);
        }

        synchronized void put(Position position, TileId tileId) {
//            System.out.println("Screen.put");
            if (tileId == TileId.EMPTY) {
                map.remove(position);
            } else {
                map.put(position, tileId);
            }
            if (invalidatedCallback != null) {
                invalidatedCallback.run();
            }
        }

        public int maxY() {
            return map.keySet().stream().mapToInt(p -> p.y).max().orElse(0);
        }
        public int maxX() {
            return map.keySet().stream().mapToInt(p -> p.x).max().orElse(0);
        }

        public TileId get(Position position) {
            return map.getOrDefault(position, TileId.EMPTY);
        }

        public void setOnInvalidated(Runnable invalidatedCallback) {
            this.invalidatedCallback = invalidatedCallback;
        }

        public List<Position> findPaddle() {
            return map.entrySet().stream()
                    .filter(e -> e.getValue() == TileId.HORIZONTAL_PADDLE)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        }

        public Optional<Position> findBall() {
            return map.entrySet().stream()
                    .filter(e -> e.getValue() == TileId.BALL)
                    .map(Map.Entry::getKey)
                    .findAny();
        }

        synchronized Screen copy() {
            Screen copy = new Screen();
            map.forEach(copy::put);
            return copy;
        }
    }



}
