//
//  GraphView.h
//  Calculator
//
//  Created by Andrew Grossnickle on 2/5/15.
//  Copyright (c) 2015 agrossnickle_gwalker. All rights reserved.
//

#import <UIKit/UIKit.h>

@class GraphView;

@protocol GraphViewDataSource
- (float)YValueForXValue:(float)xValue inGraphView:(GraphView *)sender;
- (void)storeScale:(float)scale ForGraphView: (GraphView *)sender;
- (void)storeAxisOriginX:(float)x andAxisOriginY:(float)y ForGraphView: (GraphView *)sender;

@end

@interface GraphView : UIView

@property(nonatomic, weak) IBOutlet id <GraphViewDataSource> dataSource;
@property(nonatomic) CGFloat scale;
@property(nonatomic) CGPoint axisOrigin;


@end
